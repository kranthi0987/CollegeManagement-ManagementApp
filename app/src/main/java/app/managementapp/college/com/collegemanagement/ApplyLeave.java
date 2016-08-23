package app.managementapp.college.com.collegemanagement;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import app.managementapp.college.com.collegemanagement.management.ManagementHome;
import app.managementapp.college.com.collegemanagement.model.GlobalData;
import app.managementapp.college.com.collegemanagement.model.util.Converter;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;
import app.managementapp.college.com.collegemanagement.util.ErrorToaster;

import lib.MultiSelectionSpinner;

public class ApplyLeave extends AppCompatActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener, DialogInterface.OnClickListener {
    private static final String DEBUG_TAG = "ApplyLeave";

    Context ctx;
    FrameLayout progressBarHolder;
    private CredentialManager credentialManager;
    Exception error;
    MultiSelectionSpinner multiSelectionSpinner;
    String loginURL = "";
    String[] approverArray;
    String[] leaveTypeArray;


    //UI References
    private EditText fromDateEtxt;
    private EditText fromTimeEtxt;
    private EditText toDateEtxt;
    private EditText toTimeEtxt;

    String fromDate = "";
    String toDate = "";
    String fromTime = "";
    String toTime = "";

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat dateURLFormatter;
    String errorMessage = "Fill required details";

    @Override
    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
        moveToLanding();

    }

    private void moveToLanding() {
        Intent i = new Intent(ApplyLeave.this, ManagementHome.class);
        startActivity(i);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);
        ctx = this;
        credentialManager = new CredentialManager(ctx);
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        loginURL = credentialManager.getUniversityUrl() + "/AuthenticationService.svc/AuthenticateRequest?username="+ credentialManager.getUserName() +"&Password="+ credentialManager.getPassword();
        clickSetup();

        makeNetworkCall();

    }

    private void clickSetup() {
        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToLanding();
            }
        };
        ImageView backTimeTable = (ImageView)findViewById(R.id.backTimeTable);
        backTimeTable.setOnClickListener(onFilterbackTimeTableclickListener);


//        multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.apply_leave_spinner);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateURLFormatter = new SimpleDateFormat("dd%20MMM%20yyyy", Locale.US);
        findViewsById();
        setDateTimeField();

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    Intent i = new Intent(ApplyLeave.this, ApplyLeaveAlternativeSelection.class);
                    i.putExtra("fromDate", fromDate);
                    i.putExtra("toDate", toDate);
                    i.putExtra("fromTime", fromTime);
                    i.putExtra("toTime", toTime);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validate(){
        if(fromDate == "" || toDate == "") return false;
        return true;
    }


    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.fromDate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromTimeEtxt = (EditText) findViewById(R.id.fromTime);
        fromTimeEtxt.setInputType(InputType.TYPE_NULL);
        fromTimeEtxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ApplyLeave.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        fromTimeEtxt.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        //fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.toDate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
        toTimeEtxt = (EditText) findViewById(R.id.toTime);
        toTimeEtxt.setInputType(InputType.TYPE_NULL);
        toTimeEtxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ApplyLeave.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        toTimeEtxt.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fromDatePickerDialog.show();
            }
        });
        toDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toDatePickerDialog.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
                fromDate = dateURLFormatter.format(newDate.getTime());
                Log.d(DEBUG_TAG, "onDateSet: " + fromDate);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
                toDate = dateURLFormatter.format(newDate.getTime());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    private void makeNetworkCall(){
        // Make network call
        if (isNetworkAvailable()) {
            String getLeaveApproverList = credentialManager.getUniversityUrl() + "/StaffAttendanceService.svc/GetLeaveApproverList";
            String getLeavetype = credentialManager.getUniversityUrl() + "/StaffAttendanceService.svc/GetLeavetype";
            Log.d(DEBUG_TAG, "makeNetworkCall: url: " + getLeaveApproverList);
            progressBarHolder.setVisibility(View.VISIBLE);
            new ApplyLeaveTask().execute(loginURL, getLeaveApproverList, getLeavetype);
        } else {
            Toast.makeText(ctx, "Network not available.", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connMgr = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) return true;
        return false;
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        //Toast.makeText(this, strings.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }


    // Network code
    private class ApplyLeaveTask extends AsyncTask<String, Void, String> {
        private JSONArray dataList;

        public void useLoginToken(String result){
            if(new ErrorToaster().toastError(error,ctx)) return;
            try {
                JSONObject resultJSON = new JSONObject(result);
                Log.d("resultJSON ", result + "");
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
                    i = new Intent(ApplyLeave.this, LoginActivity.class);

                    startActivity(i);
                    // kill current activity
                    finish();
                }
            } catch (Exception t) {
                error = t;
                Log.e("useLoginToken", t.getMessage() + " Could not parse malformed JSON: \"" + result + "\"");
            }
        }

        public void useGetLeaveApproverList(String result){
            if(new ErrorToaster().toastError(error,ctx)) return;
            try {
                JSONObject resultJSON = new JSONObject(result);
                Log.d("resultJSON ", "useGetLeaveApproverList: " + result + "");
                Intent i;
                if(resultJSON.getInt("ServiceResult") == 0) {

                    approverArray = new Converter().getApproverArray(resultJSON.getJSONArray("DataList"));
                } else {
                    Log.d(DEBUG_TAG, "onPostExecute: The user is not valid ==> use: " + credentialManager.getUserName() +
                            ", password: " + credentialManager.getPassword() );
                    i = new Intent(ApplyLeave.this, LoginActivity.class);

                    startActivity(i);
                    // kill current activity
                    finish();
                }
            } catch (Exception t) {
                error = t;
                Log.e("useGetLeaveApproverList", t.getMessage() + " Could not parse malformed JSON: \"" + result + "\"");
            }
        }


        public void useGetLeavetype(String result){
            if(new ErrorToaster().toastError(error,ctx)) return;
            try {
                JSONObject resultJSON = new JSONObject(result);
                Log.d("resultJSON ", "useGetLeavetype: " + result + "");
                Intent i;
                if(resultJSON.getInt("ServiceResult") == 0) {

                    leaveTypeArray = new Converter().getLeaveTypeArray(resultJSON.getJSONArray("DataList"));
                } else {
                    Log.d(DEBUG_TAG, "onPostExecute: The user is not valid ==> use: " + credentialManager.getUserName() +
                            ", password: " + credentialManager.getPassword() );
                    i = new Intent(ApplyLeave.this, LoginActivity.class);

                    startActivity(i);
                    // kill current activity
                    finish();
                }
            } catch (Exception t) {
                error = t;
                Log.e("useGetLeavetype", t.getMessage() + " Could not parse malformed JSON: \"" + result + "\"");
            }
        }
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                String loginData = downloadUrl(urls[0]);
                Log.d(DEBUG_TAG, "doInBackground: " + loginData);
                useLoginToken(loginData);
                useGetLeaveApproverList(downloadUrl(urls[1]));
                String x = downloadUrl(urls[2]);
                useGetLeavetype(x);
                return downloadUrl(x);
            } catch (Exception e) {
                error = e;
                Log.d(DEBUG_TAG, "The response is: " + e.toString());
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            progressBarHolder.setVisibility(View.GONE);
            try {
                Spinner spinner = (Spinner) findViewById(R.id.leave_type);
                if(leaveTypeArray != null & leaveTypeArray.length > 0) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,
                            android.R.layout.simple_spinner_item, leaveTypeArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    Log.d("yesy", "onPostExecute: ");
                }
                Spinner multiSelectionSpinner = (Spinner) findViewById(R.id.apply_leave_spinner);
                if(leaveTypeArray != null & leaveTypeArray.length > 0) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,
                            android.R.layout.simple_spinner_item, approverArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    multiSelectionSpinner.setAdapter(adapter);
                    Log.d("yesy", "onPostExecute: ");
                }

//                multiSelectionSpinner.setItems(approverArray);
//                multiSelectionSpinner.setListener((MultiSelectionSpinner.OnMultipleItemsSelectedListener) ctx);

                JSONObject resultJSON = new JSONObject(result);
                if(resultJSON.getInt("ServiceResult") == 0) {
                    Log.d(DEBUG_TAG, "onPostExecute: from network" );
                } else {

                }
            } catch (Exception t) {
                if(result.equals("Unable to retrieve web page. URL may be invalid.")){

                } else {
                    error = t;
                    Log.e("JSON error", t + " Could not parse malformed JSON: \"" + result + "\"");
                }
            }
        }
    }



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
            CredentialManager credentialManager = new CredentialManager(ctx);
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
}
