package app.managementapp.college.com.collegemanagement;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.managementapp.college.com.collegemanagement.adapters.MenuGridAdapter;
import app.managementapp.college.com.collegemanagement.adapters.StudentListAdapter;
import app.managementapp.college.com.collegemanagement.model.ClassData;
import app.managementapp.college.com.collegemanagement.model.GlobalData;
import app.managementapp.college.com.collegemanagement.model.StudentItem;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;

public class AttendanceForClass extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context context;
    StudentListAdapter studentListAdapter;
    public String showingCalander;
    String loginURL;
    FrameLayout progressBarHolder;
    private static final String DEBUG_TAG = "AttendanceForClass";
    private String url = "/AccademicService.svc/GetStudentList?ClassScheduleID=";
    private String updateURL =  "/AccademicService.svc/UpdateStudentAttendance";
    CredentialManager credentialManager;
    String errors1 = "something went wrong";
    String send = "";
    String ClassSchedID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_attendance_for_class);
        context = this;


        credentialManager = new CredentialManager(this);
        loginURL = credentialManager.getUniversityUrl() + "/AuthenticationService.svc/AuthenticateRequest?username="+ credentialManager.getUserName() +"&Password="+ credentialManager.getPassword();
        url = credentialManager.getUniversityUrl() + url;
        updateURL = credentialManager.getUniversityUrl() + updateURL;
        ImageView backTimeTable = (ImageView)findViewById(R.id.backTimeTable);
        backTimeTable.setOnClickListener(onFilterbackTimeTableclickListener);

        ((Button) findViewById(R.id.submitAttandance)).setOnClickListener(onSubmitclickListener);
        ((Button) findViewById(R.id.saveAttandance)).setOnClickListener(onSaveClickListener);
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);


        ((TextView) findViewById(R.id.title)).setText(getIntent().getExtras().getString("title"));
        ClassSchedID = getIntent().getExtras().getString("ClassSchedID");
        url = url + ClassSchedID;
//        makeNetworkCall(url + getIntent().getExtras().getString("ClassSchedID"));
        showingCalander = getIntent().getExtras().getString("showingCalander");
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        try {
            String cache = credentialManager.getTimeTableUpdateCache();

            JSONObject cacheJSON = new JSONObject();
            Log.d("yes", "onPostCreate: cache: " + cache);
            if(cache != ""  ) {
                cacheJSON = new JSONObject(cache);
                Log.d("yes", "onPostCreate: saveMarksCache: " + cacheJSON);
                if (cacheJSON.has(ClassSchedID)) {

                    List<StudentItem> studentsList = getStudentsListFromCache(((JSONObject) cacheJSON.get(ClassSchedID)).getJSONArray("DataList"));
                    setStudents(studentsList);
                    //                makeNetworkCall(url);
                    Log.d(DEBUG_TAG, "onPostCreate: cacheJSON: " + cacheJSON + " " + studentsList.size());
                } else {
                    makeNetworkCall(url);
                }
            } else {
                makeNetworkCall(url);
            }
        } catch (Exception e) {
            Toast.makeText(AttendanceForClass.this, "something went wrong", Toast.LENGTH_SHORT).show();
            Log.d(DEBUG_TAG, "onPostCreate: " + e);
        }
    }

    @Override
    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
        moveToTimeTable();
    }

    private void moveToTimeTable() {
        if(studentListAdapter == null || studentListAdapter.getSaved()) {
            Intent i = new Intent(AttendanceForClass.this, TimeTableV2SingleDetails.class);
            i.putExtra("showingCalander", showingCalander);
            i.putExtra("MyClass", getIntent().getExtras().getString("MyClass"));
            startActivity(i);
            finish();
        } else {
            Toast.makeText(AttendanceForClass.this, "No changes are saved, If you want to quit press once again.", Toast.LENGTH_LONG).show();
        }
    }

    View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
        public static final String DEBUG_TAG = "TimeTable";
        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
            moveToTimeTable();
        }
    };
    private void makeNetworkCall(String url) {
        progressBarHolder.setVisibility(View.VISIBLE);
//        url = credentialManager.frameUniversityUrl(url);
        Log.d(DEBUG_TAG, "url: " + url);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new AttendanceTask(context).execute(loginURL, url);

        } else {
            //"No network connection available.");
        }
    }

    public void setStudents(List<StudentItem> studentsList) {
        recyclerView = (RecyclerView) findViewById(R.id.studentCont);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        studentListAdapter = new StudentListAdapter(context, studentsList);
        recyclerView.setAdapter(studentListAdapter);
    }


    // Network code
    private class AttendanceTask extends AsyncTask<String, Void, String> {
        private Context mContext;
        private String url;
        public AttendanceTask (Context context){
            mContext = context;
        }

        public void useLoginToken(String result){
            try {
                JSONObject resultJSON = new JSONObject(result);
                Log.d("resultJSON", result + "");
                Intent i;
                if(resultJSON.getInt("ServiceResult") == 0) {
                    Log.d(DEBUG_TAG, "onPostExecute: The user is logged in ==> use: " + credentialManager.getUserName() +
                            ", password: " + credentialManager.getPassword());
                    Time requestInitiatedTime = new Time();
                    GlobalData globalData = new GlobalData();
                    globalData.setLastNetworkCall(requestInitiatedTime);
                    globalData.setToken(resultJSON.getString("Token"));
                    credentialManager.setToken(resultJSON.getString("Token"));
                    Log.d(DEBUG_TAG, "The token is: " + resultJSON.getString("Token"));
                } else {
                    Log.d(DEBUG_TAG, "onPostExecute: The user is not valid ==> use: " + credentialManager.getUserName() +
                            ", password: " + credentialManager.getPassword() );
                    i = new Intent(AttendanceForClass.this, LoginActivity.class);
                    startActivity(i);
                    // kill current activity
                    finish();
                }
            } catch (Throwable t) {
                Log.e("JSON error", t.getMessage());
            }
        }
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                String loginData = downloadUrl(urls[0]);
                useLoginToken(loginData);
                url = urls[1];
                if(url.equals(updateURL)) return downloadUrl2(url);
                return downloadUrl(url);
            } catch (Exception e) {
                Log.d(DEBUG_TAG, "The response is: " + e.toString());
                return "Unable to retrieve web page. URL may be invalid.";

            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            progressBarHolder.setVisibility(View.GONE);
            try {

                if(url.equals(updateURL)) {
                    if(result.equals(errors1)){
                        Toast.makeText(AttendanceForClass.this, errors1, Toast.LENGTH_LONG).show();
                    }
                    JSONObject resultJSON = new JSONObject(result);
                    if(resultJSON.getInt("ServiceResult") == 0) {
                        Toast.makeText(AttendanceForClass.this, "updated the changes", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AttendanceForClass.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                    moveToTimeTable();
                    return;
                }
                JSONObject resultJSON = new JSONObject(result);
                JSONObject getStudentListResult = resultJSON.getJSONObject("GetStudentListResult");
                /*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.studentCont);
                LayoutInflater inflater = LayoutInflater.from(mContext);*/

                Log.d("resultJSON ", result + "");
                if(getStudentListResult.getInt("ServiceResult") == 0) {
                    List<StudentItem> studentsList = getStudentsList(getStudentListResult.getJSONArray("DataList"));
                    setStudents(studentsList);
                } else {

                }
            } catch (Exception t) {
                Log.e("JSON error", t + "");
            }
        }
    }

    private List<StudentItem> getStudentsList(JSONArray dataList) {
        List<StudentItem> data = new ArrayList<>();
        for (int i = 0, size = dataList.length(); i < size; i++)
        {
            try {
                JSONObject objectInArray = dataList.getJSONObject(i);
                StudentItem studentItem = new StudentItem(objectInArray.getString("CardNo"), objectInArray.getString("Code"), objectInArray.getString("DateOfBirth"),
                        objectInArray.getString("FirstName"), objectInArray.getString("FullName"), objectInArray.getString("Gender"),
                        objectInArray.getString("ID"), objectInArray.getString("LastName"), objectInArray.getString("MGUID"), objectInArray.getString("MiddleName"),
                        objectInArray.getString("Address"), objectInArray.getString("Email"), objectInArray.getString("Phone"), null, null, 0);
                data.add(studentItem);
            } catch (Exception e) {
                Log.e(DEBUG_TAG, "getStudentsList: " + e.getMessage());
            }
        }
        return data;
    }


    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 50000;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);

            if(loginURL.equals(myurl)){
                conn.setRequestMethod("POST");
            } else {
                conn.setRequestMethod("GET");
                conn.setRequestProperty("TOKEN", credentialManager.getToken());
            }
//            GlobalData globalData = new GlobalData();
            conn.setDoInput(true);
//            conn.setRequestProperty("Content-Type", "application/json");
            // Starts the query
            conn.connect();


            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "error 1 is --: " + e.toString());
        }
        finally {
            if (is != null) is.close();
            if (os != null) os.close();
        }
        return "";
    }
    private String downloadUrl2(String myurl) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 50000;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn2 = (HttpURLConnection) url.openConnection();
            conn2.setReadTimeout(10000 /* milliseconds */);
            conn2.setConnectTimeout(15000 /* milliseconds */);
            conn2.setRequestMethod("POST");
//            GlobalData globalData = new GlobalData();
            conn2.setRequestProperty("TOKEN", credentialManager.getToken());
            conn2.setDoInput(true);
            conn2.setRequestProperty("Content-Type", "application/json");
            // Starts the query
            conn2.connect();

            if(updateURL.equals(myurl)){
                //setup send
                os = new BufferedOutputStream(conn2.getOutputStream());
                os.write(send.getBytes());
                //clean up
                os.flush();
            }

            int response = conn2.getResponseCode();
            if(response == 200){
                is = conn2.getInputStream();
                String contentAsString = readIt(is, len);
                Log.d(DEBUG_TAG, "downloadUrl2: success: " + contentAsString);
                return contentAsString;
            } else {

                Log.d(DEBUG_TAG, "downloadUrl2: response: " + response);
                InputStream error = conn2.getErrorStream();
                Log.d(DEBUG_TAG, "downloadUrl2: error: " + readIt(error, len));
                errors1 = readIt(error, len);// + "; response: " + response;
                return errors1;
            }
//            is = conn2.getInputStream();
            // Convert the InputStream into a string
//            String contentAsString = readIt(is, len);
//            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "error2 is --: " + e.toString());
        }
        finally {
            if (is != null) is.close();
            if (os != null) os.close();
        }
        return "";
    }


    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        /*char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);*/
        BufferedReader r = new BufferedReader(reader);
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return  total.toString();
    }

    View.OnClickListener onSubmitclickListener = new View.OnClickListener() {
        public static final String DEBUG_TAG = "TimeTable";
        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG, "onClick: onFilterclickListener");
            List<StudentItem> studentMarksList = studentListAdapter.getStudentList();

            Iterator<StudentItem> it = studentMarksList.iterator();
            send = "[";
            boolean firstIt = false;
            while (it.hasNext()){
                if(firstIt) send += ",";
                firstIt = true;
                StudentItem studentMarksItem = it.next();
                send += "{\"ID\":\"" + studentMarksItem.getID();
                send += "\",\"ClassSchedID\":\"" + getIntent().getExtras().getString("ClassSchedID");
                send += "\",\"ClassDate\":\"" + getIntent().getExtras().getString("intentClassDate");
                send += "\",\"StartTime\":\"" + getIntent().getExtras().getString("intentStartTime");
                send += "\",\"EndTime\":\"" + getIntent().getExtras().getString("intentEndTime");
                send += "\",\"Comments\":\"" + "";
                send += "\",\"IsPresent\":\"" + studentMarksItem.getPresent() + "\"}";
            }
            send += "]";
            Log.d(DEBUG_TAG, "onClick: send: " + send);
            makeNetworkCall(updateURL);

//            ConnectivityManager connMgr = (ConnectivityManager)
//                    getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isConnected()) {
//                new AttendanceUpdateTask(context).execute(updateURL);
//            } else {
//                //"No network connection available.");
//            }
            Log.d(DEBUG_TAG, "onClick: onFilterclickListener" + send);
//            moveToTimeTable();
        }
    };

    View.OnClickListener onSaveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG, "onClick: onFilterclickListener");
            List<StudentItem> studentMarksList = studentListAdapter.getStudentList();

            Iterator<StudentItem> it = studentMarksList.iterator();
            send = "{\"DataList\":[";
            boolean firstIt = false;
            while (it.hasNext()){
                if(firstIt) send += ",";
                firstIt = true;
                StudentItem studentMarksItem = it.next();
                send += "{\"ID\":\"" + studentMarksItem.getID();
                send += "\",\"Code\":\"" + studentMarksItem.getCode();
                send += "\",\"FullName\":\"" + studentMarksItem.getFullName();
                send += "\",\"IsPresent\":\"" + studentMarksItem.getPresent() + "\"}";
            }
            send += "]}";
            Log.d(DEBUG_TAG, "onClick: send: " + send);
            try {
                saveMarksCache(getIntent().getExtras().getString("ClassSchedID"), new JSONObject(send));
                Toast.makeText(AttendanceForClass.this, "Saved your changes", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {

            }
//                moveToInternalExamDetails();
        }
    };


    private void saveMarksCache(String classSchedID, JSONObject resultJSON) {
        try {
            studentListAdapter.setSaved(true);
            String cache = credentialManager.getTimeTableUpdateCache();
            JSONObject finalJSon = new JSONObject();
            if(cache != "" ) finalJSon = new JSONObject(cache);
            finalJSon.put(classSchedID, resultJSON);
            credentialManager.setTimeTableUpdateCache(finalJSon.toString());
            Log.d("yes", "saveMarksCache: " + finalJSon);
        } catch (Exception e) {
            Log.d("yes", "saveMarksCache: " + e);
        }
    }


    private List<StudentItem> getStudentsListFromCache(JSONArray dataList) {
        List<StudentItem> data = new ArrayList<>();
        for (int i = 0, size = dataList.length(); i < size; i++)
        {
            try {
                JSONObject objectInArray = dataList.getJSONObject(i);
                StudentItem studentItem = new StudentItem(null, objectInArray.getString("Code"), null,
                        null, objectInArray.getString("FullName"), null,
                        null, null, null, null,
                        null, null, null, null, objectInArray.getString("IsPresent"), 0);
                data.add(studentItem);
            } catch (Exception e) {
                Log.e(DEBUG_TAG, "getStudentsList: " + e.getMessage());
            }
        }
        return data;
    }
}
