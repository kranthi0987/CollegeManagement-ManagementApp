package app.managementapp.college.com.collegemanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import app.managementapp.college.com.collegemanagement.management.ManagementHome;
import app.managementapp.college.com.collegemanagement.model.ClassData;
import app.managementapp.college.com.collegemanagement.model.GlobalData;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;

public class TimeTableV2 extends FragmentActivity {

    private static final String DEBUG_TAG = "TimeTable";
    private Map<String, List<ClassData>> data;
    RecyclerView recyclerView;
    LinearLayout dateView;
    List<View> views = new ArrayList();
    ImageButton lastAcitveDateImage;
    LinearLayout lastAcitveDateRipple;
    TextView lastMonth;
    TextView lastDate;
    LinearLayout layoutOfPopup; PopupWindow popupMessage; Button popupButton, insidePopupButton; TextView popupText; TextView toText;
    DatePicker fromDatePicker; DatePicker toDatePicker;
    String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    CustomCalendarView calendarView;
    Context ctx;
    int currentMonth = 0;
    Date initDate;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    FrameLayout progressBarHolder;
    Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
    CredentialManager credentialManager;
    String loginURL;
    @Override
    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
        if (popupMessage.isShowing()) {
            popupMessage.dismiss();
        } else {
            moveToLanding();
        }
    }

    private void moveToLanding() {
        Intent i = new Intent(TimeTableV2.this, ManagementHome.class);
        startActivity(i);
        finish();
    }

    private Map<String, List<ClassData>> getData() {
        Map<String, List<ClassData>> data = new LinkedHashMap<String, List<ClassData>>();
        List<ClassData> classDatas1 = new ArrayList<>();
        classDatas1.add(getClassD(1, "12/04/2016"));
        classDatas1.add(getClassD(2, "12/04/2016"));
        data.put("12/04/2016", classDatas1);
        List<ClassData> classDatas2 = new ArrayList<>();
        classDatas2.add(getClassD(1, "13/04/2016"));
        data.put("13/04/2016", classDatas2);
        List<ClassData> classDatas3 = new ArrayList<>();
        classDatas3.add(getClassD(1, "14/04/2016"));
        classDatas3.add(getClassD(2, "14/04/2016"));
        data.put("14/04/2016", classDatas3);
        List<ClassData> classDatas4 = new ArrayList<>();
        classDatas4.add(getClassD(1, "15/04/2016"));
        data.put("15/04/2016", classDatas4);
        List<ClassData> classDatas5 = new ArrayList<>();
        classDatas5.add(getClassD(1, "16/04/2016"));
        classDatas5.add(getClassD(2, "16/04/2016"));
        data.put("16/04/2016", classDatas5);
        return data;
    }

    private ClassData getClassD(int id, String date) {
        ClassData classData =  new ClassData();
//        if(id == 1) classData = new ClassData("4", date, "1256", "MA101", date, "10:00 AM", "Building 1 - Room 2", "Sec A", date, "09:00 AM", "Financial Accounting");
//        if(id == 2) classData = new ClassData("4", date, "1256", "MA101", date, "12:00 AM", "Building 1 - Room 6", "Sec C", date, "11:00 AM", "Financial Accounting");
        return classData;
    }


    private String getFormattedDate(DatePicker fromDatePicker) {
        // format API requires is: 30 Apr 2016
        int day = fromDatePicker.getDayOfMonth();
        int month = fromDatePicker.getMonth();
        int year = fromDatePicker.getYear();
        String formatted = "";
        if(day < 10){
            formatted  += "0" + day ;
        } else {
            formatted  += day;
        }
        if(month < 10){
            formatted += "%20" + months[month];
        } else {
            formatted += "%20" + month;
        }
        formatted += "%20" + year;
        return formatted;
    }

    private boolean validateDates() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_v2);
        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        ctx = this;
        initDate = new Date();
        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToLanding();
            }
        };

        credentialManager = new CredentialManager(ctx);
        loginURL = credentialManager.getUniversityUrl() + "/AuthenticationService.svc/AuthenticateRequest?username="+ credentialManager.getUserName() +"&Password="+ credentialManager.getPassword();


        View.OnClickListener onFilterOkclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterOkclickListener");
                if (popupMessage.isShowing()) {
                    popupMessage.dismiss();
                    /*if(validateDates()) {
                        popupMessage.dismiss();
                        String from = getFormattedDate(fromDatePicker);
                        String to = getFormattedDate(toDatePicker);
                        String url =  "http://scdemo.saas.talismaonline.com/MobileApp/service/AccademicService.svc/GetClassSchedule?StartDate="
                                + from + "&EndDate=" + to;
                        Log.d(DEBUG_TAG, "ul: " + url);
//                        url="http://scdemo.saas.talismaonline.com/MobileApp/service/AccademicService.svc/GetClassSchedule?StartDate=01%20Apr%202016&EndDate=30%20Apr%202016";
                        ConnectivityManager connMgr = (ConnectivityManager)
                                getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.isConnected()) {
                            new TimeTableTask().execute(url);
                        } else {
                            //"No network connection available.");
                        }
                    }*/

                }
            }
        };


        popupText = new TextView(this);
        toText = new TextView(this);
        insidePopupButton = new Button(this);
        layoutOfPopup = new LinearLayout(this);
        insidePopupButton.setText("OK");
        popupText.setText("From Date");;
        toText.setText("To Date");
//        popupText.setTextColor(Color.parseColor("#ffffff"));
//        toText.setTextColor(Color.parseColor("#ffffff"));
        popupText.setPadding(0, 0, 0, 20); layoutOfPopup.setOrientation(LinearLayout.VERTICAL);
        fromDatePicker = new DatePicker(this);
        toDatePicker = new DatePicker(this);
        fromDatePicker.setCalendarViewShown(false);
        toDatePicker.setCalendarViewShown(false);
        layoutOfPopup.addView(popupText);
        layoutOfPopup.addView(fromDatePicker);
        layoutOfPopup.addView(toText);
        layoutOfPopup.addView(toDatePicker);
        layoutOfPopup.addView(insidePopupButton);
        layoutOfPopup.setBackgroundColor(Color.parseColor("#ffffff"));
        layoutOfPopup.setPadding(20,20,20,20);

        popupMessage = new PopupWindow(layoutOfPopup, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupMessage.setContentView(layoutOfPopup);



        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dateContainer);
        LayoutInflater inflater = LayoutInflater.from(this);

        View.OnClickListener onFilterclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterclickListener");
//                popupMessage.showAsDropDown(insidePopupButton, 0, 0);
                popupMessage.showAtLocation(insidePopupButton, Gravity.CENTER, 0, 0);
            }
        };

        ImageView filter = (ImageView)findViewById(R.id.filter);
        filter.setOnClickListener(onFilterclickListener);
        ImageView backTimeTable = (ImageView)findViewById(R.id.backTimeTable);
//        popupMessage.showAsDropDown(insidePopupButton, 0, 0);

        backTimeTable.setOnClickListener(onFilterbackTimeTableclickListener);
        insidePopupButton.setOnClickListener(onFilterOkclickListener);


        data = getData();
        setTheTimeTableV2Screen(data, "03/May/2015", "09/May/2016");

        //Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date d) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                Toast.makeText(InvigilationDetails.this, df.format(d), Toast.LENGTH_SHORT).show();
                if(data != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    String ds = "";
                    ds = cal.get(Calendar.DATE) + "/";
                    if (("" + cal.get(Calendar.MONTH)).length() == 1) ds += "0";
                    ds += cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
                    Log.d(DEBUG_TAG, "decorate: " + ds + " " + data.containsKey(ds));
                    String singleD = cal.get(Calendar.DATE) + "/";
                    if (("" + cal.get(Calendar.MONTH)).length() == 1) singleD += "0";
                    singleD += cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
                    if (data.containsKey(ds)) {
                        Intent i = new Intent(TimeTableV2.this, TimeTableV2SingleDetails.class);
                        //TODO: send data
                        /*List<InternalInvigilationItem> duties = data.get(ds);
                        String dt = new Converter().invigilationDetailstoJson(duties);
                        i.putExtra("MyClass", dt);*/
                        i.putExtra("dateTitle", singleD);
                        startActivity(i);
                        finish();
                    }
                }
            }

            @Override
            public void onMonthChanged(Date d) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);

                List decorators = new ArrayList<>();
                decorators.add(new ColorDecorator());
                calendarView.setDecorators(decorators);
                int m = 0;
                if (initDate.getMonth() == 12 && d.getMonth() == 1) {
                    currentCalendar.add(Calendar.MONTH, 1);
                } else if (initDate.getMonth() > cal.get(Calendar.MONTH)) {
                    currentCalendar.add(Calendar.MONTH, -1);
                } else {
                    currentCalendar.add(Calendar.MONTH, 1);
                }
                initDate = d;
                calendarView.refreshCalendar(currentCalendar);
                currentMonth = currentCalendar.get(Calendar.MONTH);
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
//                Toast.makeText(InvigilationDetails.this, df.format(d), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class ColorDecorator implements DayDecorator {
        @Override
        public void decorate(DayView dayView) {
            Date d = dayView.getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            String ds = "";
            ds = cal.get(Calendar.DATE) + "/" ;
            if(("" + cal.get(Calendar.MONTH)).length() == 1) ds += "0";
            ds += cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
//            String ds = d.getYear() + " " + d.getMonth() + " " + d.getDate();
            Log.d(DEBUG_TAG, "decorate: " + ds +  " "+ data.containsKey(ds));
            if(data.containsKey(ds)) {
                dayView.setBackgroundColor(getResources().getColor(R.color.colorLightButton));
            } else {
                dayView.setBackgroundColor(getResources().getColor(R.color.grey_bg));
            }
        }
    }
    private void setTheTasks(String from, String to, int tasks) {
        Log.d(DEBUG_TAG, "setTheTasks: " + from + " " + to + " " + tasks);
        if(from != null) ((TextView) findViewById(R.id.fromRange)).setText(from);
        if(to != null) ((TextView) findViewById(R.id.toRange)).setText(to);
        if(tasks != -1) ((TextView) findViewById(R.id.tasksCount)).setText("" + tasks);
    }


    private void setTheTimeTableV2Screen(Map<String, List<ClassData>> result, String from, String to) {

        try {

            //adding calendar day decorators
            List decorators = new ArrayList<>();
            decorators.add(new ColorDecorator());
            calendarView.setDecorators(decorators);
            // set current month
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = (String) result.keySet().toArray()[0];
            Calendar toC = Calendar.getInstance();
            toC.setTime(formatter.parse(dateInString));
            toC.add(Calendar.MONTH, 1);
            Calendar CurrentDate = Calendar.getInstance(Locale.getDefault());
            currentCalendar.add(Calendar.MONTH, monthsBetween(CurrentDate, toC));

        } catch (Exception e) {
            Log.d(DEBUG_TAG, "setTheInvigilationScreen: Date issue" );
        }
        currentMonth = currentCalendar.get(Calendar.MONTH);
        currentCalendar = currentCalendar;
        calendarView.refreshCalendar(currentCalendar);
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        progressBarHolder.setVisibility(View.INVISIBLE);
        //TODO: dummy 8  is passed remove
        setTheTasks(from, to, 8);
    }

    public int monthsBetween(Calendar CurrentDate,Calendar toDate) {
        Log.d(DEBUG_TAG, "monthsBetween:  ====" + CurrentDate.get(Calendar.YEAR) + " " +CurrentDate.get(Calendar.MONTH) + " " + CurrentDate.get(Calendar.DATE));
        Log.d(DEBUG_TAG, "monthsBetween:  ====" + toDate.get(Calendar.YEAR) + " " +toDate.get(Calendar.MONTH) + " " + toDate.get(Calendar.DATE));
        int y = Math.abs(CurrentDate.get(Calendar.YEAR) - toDate.get(Calendar.YEAR));
        int m = (y * 12) + CurrentDate.get(Calendar.MONTH) - toDate.get(Calendar.MONTH);
        if(CurrentDate.get(Calendar.YEAR)  <= toDate.get(Calendar.YEAR)) {
            m = Math.abs(m);
        } else {
            m = -1 * m;
        }
        return  m;
    }


    // Network code
    private class TimeTableTask extends AsyncTask<String, Void, String> {

        public void useLoginToken(String result){
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
                    i = new Intent(TimeTableV2.this, LoginActivity.class);

                    startActivity(i);
                    // kill current activity
                    finish();
                }
            } catch (Throwable t) {
                Log.e("JSON error", t.getMessage() + " Could not parse malformed JSON: \"" + result + "\"");
            }
        }
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                String loginData = downloadUrl(urls[0]);
                useLoginToken(loginData);
                return downloadUrl(urls[1]);
            } catch (Exception e) {
                Log.d(DEBUG_TAG, "The response is: " + e.toString());
                return "Unable to retrieve web page. URL may be invalid.";

            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

//            credentialManager = new CredentialManager(this);
            try {
                JSONObject resultJSON = new JSONObject(result);
                Log.d("resultJSON ", result + "");
                /*if(resultJSON.getInt("ServiceResult") == 0) {
                    //if(credentialManager.isFirstTimeUser() || credentialManager.isLoggedOutUser()){

                } else {

                }*/
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
        BufferedReader r = new BufferedReader(reader);
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return  total.toString();
    }

}
