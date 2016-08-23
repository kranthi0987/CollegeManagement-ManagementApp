package app.managementapp.college.com.collegemanagement;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
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

import app.managementapp.college.com.collegemanagement.adapters.StudentListAdapter;
import app.managementapp.college.com.collegemanagement.adapters.StudentMarksListAdapter;
import app.managementapp.college.com.collegemanagement.model.InternalExamItem;
import app.managementapp.college.com.collegemanagement.model.StudentItem;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;

public class InternalExamsMarksUpdate extends AppCompatActivity {

    public static final String DEBUG_TAG = "InternalMarksUpdate";
    private CredentialManager credentialManager;
    StudentMarksListAdapter studentMarksListAdapter;
    private String url = "/AccademicService.svc/GetStudentListForInternalMarks?ExamID=";//195";
    private String updateURL =  "/AccademicService.svc/UpdateInternalMarks";
    private RecyclerView recyclerView;
    String send;
    FrameLayout progressBarHolder;
    String examID;
    Context ctx;
    public String showingCalander;

    @Override
    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
        moveToInternalExamDetails();
    }

    private void moveToInternalExamDetails() {
        if(studentMarksListAdapter == null || studentMarksListAdapter.getSaved()) {
            Intent i = new Intent(InternalExamsMarksUpdate.this, InternalExamSingle.class);
            i.putExtra("examItemsString", getIntent().getExtras().getString("examItemsString"));
            i.putExtra("showingCalander", showingCalander);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(InternalExamsMarksUpdate.this, "No changes are saved, If you want to quit press once again.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        try {
            String cache = credentialManager.getInternalMarksUpdateCache();

            JSONObject cacheJSON = new JSONObject();
            Log.d("yes", "onPostCreate: cache: " + cache);
            if(cache != ""  ) {
                cacheJSON = new JSONObject(cache);
                Log.d("yes", "onPostCreate: saveMarksCache: " + cacheJSON);
                if (cacheJSON.has(examID)) {

                    List<StudentItem> studentsList = getStudentsListFromCache(((JSONObject) cacheJSON.get(examID)).getJSONArray("DataList"));
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
            Toast.makeText(InternalExamsMarksUpdate.this, "something went wrong", Toast.LENGTH_SHORT).show();
            Log.d(DEBUG_TAG, "onPostCreate: " + e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_exams_marks_update);
        credentialManager = new CredentialManager(this);
        ((TextView) findViewById(R.id.dateTitle)).setText(getIntent().getExtras().getString("dateTitle"));
        //TODO: remove after done
        examID = getIntent().getExtras().getString("ExamID");
//        examID = "195";

        url += examID;
        updateURL = credentialManager.getUniversityUrl() + updateURL;
        url = credentialManager.getUniversityUrl() + url;
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        ctx = this;
        recyclerView = (RecyclerView) findViewById(R.id.studentCont);
        initEvents();
        showingCalander = getIntent().getExtras().getString("showingCalander");
    }

    private void initEvents() {
        View.OnClickListener onSubmitclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterclickListener");
                List<StudentItem> studentMarksList = studentMarksListAdapter.getStudentMarksList();

                Iterator<StudentItem> it = studentMarksList.iterator();
                send = "{\"ReferenceID\": \""+ examID + "\",  \"DataList\":[";
//                send = "{\"ReferenceID\": \""+ "195" + "\",  \"DataList\":[";
//                send += "[";
                boolean firstIt = false;
                while (it.hasNext()){
                    if(firstIt) send += ",";
                    firstIt = true;
                    StudentItem studentMarksItem = it.next();
                    send += "{\"ID\":\"" + studentMarksItem.getID();
                    send += "\",\"Marks\":\"" + studentMarksItem.getMarks();
                    send += "\",\"Remarks\":\"" + studentMarksItem.getPresent() + "\"}";
                }
                send += "]}";
                Log.d(DEBUG_TAG, "onClick: send: " + send);
                makeNetworkCall(updateURL);
//                moveToInternalExamDetails();
            }
        };
        View.OnClickListener onSaveClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterclickListener");
                List<StudentItem> studentMarksList = studentMarksListAdapter.getStudentMarksList();

                Iterator<StudentItem> it = studentMarksList.iterator();
                send = "{\"ReferenceID\": \""+ examID + "\",  \"DataList\":[";
//                send = "{\"ReferenceID\": \""+ "195" + "\",  \"DataList\":[";
//                send += "[";
                boolean firstIt = false;
                while (it.hasNext()){
                    if(firstIt) send += ",";
                    firstIt = true;
                    StudentItem studentMarksItem = it.next();
                    send += "{\"ID\":\"" + studentMarksItem.getID();
                    send += "\",\"Marks\":\"" + studentMarksItem.getMarks();
                    send += "\",\"MaxMarks\":\"" + studentMarksItem.getMaxMarks();
                    send += "\",\"FullName\":\"" + studentMarksItem.getFullName();
                    send += "\",\"Code\":\"" + studentMarksItem.getCode();
                    send += "\",\"isEditable\":\"" + studentMarksItem.getIsEditable();
                    send += "\",\"Remarks\":\"" + studentMarksItem.getPresent() + "\"}";
                }
                send += "]}";
                Log.d(DEBUG_TAG, "onClick: send: " + send);
                try {
                    saveMarksCache(examID, new JSONObject(send));
                    Toast.makeText(InternalExamsMarksUpdate.this, "Saved your changes", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
//                moveToInternalExamDetails();
            }
        };
        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToInternalExamDetails();
            }
        };
        ((Button) findViewById(R.id.submitAttandance)).setOnClickListener(onSubmitclickListener);
        ((Button) findViewById(R.id.saveAttandance)).setOnClickListener(onSaveClickListener);
        ((ImageView) findViewById(R.id.backTimeTable)).setOnClickListener(onFilterbackTimeTableclickListener);
    }


    private void makeNetworkCall(String url) {
        progressBarHolder.setVisibility(View.VISIBLE);
        Log.d(DEBUG_TAG, "url: " + url);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new InternalExamMarksTask(this).execute(url);
        } else {
            //"No network connection available.");
        }
    }



    // Network code
    private class InternalExamMarksTask extends AsyncTask<String, Void, String> {
        private Context mContext;
        public InternalExamMarksTask (Context context){
            mContext = context;
        }
        String url = "";
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {

                url = urls[0];
                if(urls[0].equals(updateURL)) return downloadUrl2(urls[0]);
                return downloadUrl(urls[0]);
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
                JSONObject resultJSON = new JSONObject(result);

                if(url.equals(updateURL)) {
                    if(resultJSON.getInt("ServiceResult") == 0) {
                        Toast.makeText(InternalExamsMarksUpdate.this, "updated the changes", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(InternalExamsMarksUpdate.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                    moveToInternalExamDetails();
                    return;
                }
                Log.d("resultJSON ", result + "");
                if(resultJSON.getInt("ServiceResult") == 0) {
                    List<StudentItem> studentsList = getStudentsList(resultJSON.getJSONArray("DataList"));
                    setStudents(studentsList);
                    saveMarksCache(examID, resultJSON);
                } else {

                }
            } catch (Exception t) {
                Log.e("JSON error", t + "");
            }
        }


    }


    private void saveMarksCache(String examID, JSONObject resultJSON) {
        try {
            studentMarksListAdapter.setSaved(true);
            String cache = credentialManager.getInternalMarksUpdateCache();
            JSONObject finalJSon = new JSONObject();
            if(cache != "" ) finalJSon = new JSONObject(cache);
            finalJSon.put(examID, resultJSON);
            credentialManager.setInternalMarksUpdateCache(finalJSon.toString());
            Log.d("yes", "saveMarksCache: " + finalJSon);
        } catch (Exception e) {
            Log.d("yes", "saveMarksCache: " + e);
        }
    }
    private void setStudents(List<StudentItem> studentsList) throws JSONException {
        if(studentsList.size() == 0) {
            Toast.makeText(ctx, "No students for the exam." , Toast.LENGTH_LONG).show();
        } else {
            recyclerView = (RecyclerView) findViewById(R.id.studentCont);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            studentMarksListAdapter = new StudentMarksListAdapter(ctx, studentsList);
            recyclerView.setAdapter(studentMarksListAdapter);

        }
    }


    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 50000;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            CredentialManager credentialManager = new CredentialManager(this);
//            GlobalData globalData = new GlobalData();
            conn.setRequestProperty("TOKEN", credentialManager.getToken());
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
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
            Log.d(DEBUG_TAG, "error is --: " + e.toString());
        }
        finally {
            if (is != null) {
                is.close();
            }
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
                return "something went wrong";
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
        BufferedReader r = new BufferedReader(reader);
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return  total.toString();
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
                        null, null, null,objectInArray.getString("Marks"), objectInArray.getString("MaxMarks"), objectInArray.getString("isEditable"));
                data.add(studentItem);
            } catch (Exception e) {
                Log.e(DEBUG_TAG, "getStudentsList: " + e.getMessage());
            }
        }
        return data;
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
                        null, null, null,objectInArray.getString("Marks"), objectInArray.getString("MaxMarks"), objectInArray.getString("isEditable"));
                data.add(studentItem);
            } catch (Exception e) {
                Log.e(DEBUG_TAG, "getStudentsList: " + e.getMessage());
            }
        }
        return data;
    }


}
