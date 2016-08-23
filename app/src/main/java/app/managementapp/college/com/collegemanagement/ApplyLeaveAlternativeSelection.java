package app.managementapp.college.com.collegemanagement;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.managementapp.college.com.collegemanagement.management.ManagementHome;
import app.managementapp.college.com.collegemanagement.model.ClassData;
import app.managementapp.college.com.collegemanagement.model.Faculty;
import app.managementapp.college.com.collegemanagement.model.GlobalData;
import app.managementapp.college.com.collegemanagement.model.util.Converter;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;

public class ApplyLeaveAlternativeSelection extends AppCompatActivity {
    private static final String DEBUG_TAG = "news";
    private CredentialManager credentialManager;
    FrameLayout progressBarHolder;
    Converter converter;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave_alternative_selection);
        credentialManager = new CredentialManager(this);
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        String getClasses = credentialManager.getUniversityUrl() + "/AccademicService.svc/GetClassSchedule?StartDate=" + getIntent().getExtras().getString("fromDate") +
                "%2000:00:00%20&EndDate=" + getIntent().getExtras().getString("toDate") + "%2023:59:59";
        String getAlternates = credentialManager.getUniversityUrl() + "/StaffAttendanceService.svc/GetAlternativeStaffList?FromDate=" + getIntent().getExtras().getString("fromDate") +
                "&ToDate=" + getIntent().getExtras().getString("fromDate");
        Log.d(DEBUG_TAG, "onCreate: " + getClasses);
        Log.d(DEBUG_TAG, "onCreate: " + getAlternates);

        String loginURL = credentialManager.getUniversityUrl() + "/AuthenticationService.svc/AuthenticateRequest?username="+ credentialManager.getUserName() +"&Password="+ credentialManager.getPassword();
        converter = new Converter();
        ctx = this;
        ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

//        Toast.makeText(getContext(), "Saved Univ URL: " + credentialManager.getUniversityUrl(), Toast.LENGTH_LONG).show();
        if (networkInfo != null && networkInfo.isConnected()) {
            progressBarHolder.setVisibility(View.VISIBLE);
            new AlternativeSelectionTask().execute(loginURL, getClasses, getAlternates);
        } else {
            //TODO: offline compatible
            Log.d(DEBUG_TAG, "cache: " + credentialManager.getProfileCache());
        }


        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToLanding();
            }
        };
        ImageView backTimeTable = (ImageView)findViewById(R.id.backTimeTable);
        backTimeTable.setOnClickListener(onFilterbackTimeTableclickListener);

    }


    private void moveToLanding() {
        Intent i = new Intent(ApplyLeaveAlternativeSelection.this, ManagementHome.class);
        startActivity(i);
        finish();
    }


    // Network code
    private class AlternativeSelectionTask extends AsyncTask<String, Void, String> {
        String result1 = "";
        String result2 = "";
        String result3 = "";
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                GlobalData globalData = new GlobalData();
                result1 = downloadUrl(urls[0]);
                result2 = downloadUrl(urls[1]);
                return result3 = downloadUrl(urls[2]);
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
                Log.e(DEBUG_TAG, "resultJSON: " + result1 + "");
                Log.e(DEBUG_TAG, "resultJSON: " + result2 + "");
                Log.e(DEBUG_TAG, "resultJSON: " + result3 + "");
                JSONObject resultJSON1 = new JSONObject(result1);
                resultJSON = new JSONObject(result2);
                if(resultJSON1.getInt("ServiceResult") == 0) {
                    List<Faculty> alternates = converter.convertFacultyItemsString(result3);
//                    alternates
                    String[] alternatesArray = converter.convertFacultyArrayString(result3);// new String[0];
                    if(alternatesArray != null & alternatesArray.length == 0) {
                        Toast.makeText(ctx, "No Alternatives found", Toast.LENGTH_LONG).show();
                    }
                    Log.d(DEBUG_TAG, "onPostExecute: alternates " +alternates.size() );
                    resultJSON = new JSONObject(result2);
                    Map<String, List<ClassData>> data = converter.convertClassLeaveApplyItemsString(resultJSON.getString(("GetClassScheduleResult")));
                    if(data.containsKey("0")){
                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ClassesList);
                        LayoutInflater inflater = LayoutInflater.from(ctx);
                        List<ClassData> dataO = data.get("0");
                        Iterator<ClassData> it = dataO.iterator();
                        while (it.hasNext()) {
                            ClassData classData = it.next();
                            View view  = inflater.inflate(R.layout.row_class_alternative_selection_item, linearLayout, false);
//                            ((TextView) view.findViewById(R.id.subject)).setText(exam.getExamName().replaceAll(",",""));
//                ((TextView) view.findViewById(R.id.time1)).setText(exam.getSession());
                            // set item content in view

                            String course = "Course: ";
                            if(classData.getCourse().toString().equals("null")){
                                course += "N/A";
                            } else {
                                course += classData.getCourse();
                            }
                            ((TextView) view.findViewById(R.id.courseDesc)).setText(course);
                            String subject = "";
                            if(classData.getTitle().toString().equals("null")){
                                subject += "N/A";
                            } else {
                                subject += classData.getTitle();
                            }
                            ((TextView) view.findViewById(R.id.subject)).setText(subject);
                            ((TextView) view.findViewById(R.id.section)).setText(classData.getSection());
                            ((TextView) view.findViewById(R.id.room)).setText(classData.getPlace());
                            String time1 = "";
                            if(classData.getStartTime().toString().equals("null")){
                                time1 += "N/A";
                            } else {
                                time1 += classData.getStartTime();
                            }
                            String time2 = "";
                            if(classData.getEndTime().toString().equals("null")){
                                time2 += "N/A";
                            } else {
                                time2 += classData.getEndTime();
                            }
                            ((TextView) view.findViewById(R.id.time1)).setText(time1);
                            ((TextView) view.findViewById(R.id.time2)).setText(time2);
                            String title = subject + ", " + time1 + " : " + time2;


                            Spinner spinner = (Spinner) view.findViewById(R.id.apply_leave_spinner);
                            if(alternatesArray != null & alternatesArray.length > 0) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,
                                        android.R.layout.simple_spinner_item, alternatesArray);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(adapter);
                                Log.d("yesy", "onPostExecute: ");
                            }

                            linearLayout.addView(view);
                        }
                        Log.d("yes", "onCreate: " + dataO.size());
                    }
                    Log.d(DEBUG_TAG, "onPostExecute: " + data.size());
                } else {

                }
            } catch (Exception t) {
                Log.e("JSON error", t + " Could not parse malformed JSON: \"" + result + "\"");
            }
            /*Intent i = new Intent(LoginActivity.this, LandingDrawer.class);
            startActivity(i);*/
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
            if(myurl.contains("AuthenticateRequest")) conn.setRequestMethod("POST");
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

    private void setTheAlternativeSelectionScreen(String result) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.navigationItemsCont);
        linearLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        try {
            JSONObject resultJSON = new JSONObject(result);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject data = (JSONObject) dataList.get(i);
                View view  = inflater.inflate(R.layout.row_class_alternative_selection_item, linearLayout, false);
                ((TextView) view.findViewById(R.id.newsTitle)).setText(data.getString("NotificationTitle"));
                ((TextView) view.findViewById(R.id.newsBody)).setText(data.getString("NotificationDescription"));
                // set item content in view
                linearLayout.addView(view);
                ((TextView)findViewById(R.id.newsStatus)).setText("yes Classes to show.");
            }
            if(dataList.length() == 0){
                ((TextView)findViewById(R.id.newsStatus)).setText("No Classes to apply leave.");
            }
        } catch (Exception ex) {

        }

    }
}
