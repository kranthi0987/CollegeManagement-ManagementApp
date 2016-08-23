package app.managementapp.college.com.collegemanagement;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import app.managementapp.college.com.collegemanagement.management.ManagementHome;
import app.managementapp.college.com.collegemanagement.model.GlobalData;
import app.managementapp.college.com.collegemanagement.model.InternalExamItem;
import app.managementapp.college.com.collegemanagement.model.util.Converter;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;
import app.managementapp.college.com.collegemanagement.util.ErrorToaster;
import app.managementapp.college.com.collegemanagement.util.NetworkUtils;

public class InternalExams extends AppCompatActivity implements Serializable {
    private static final String DEBUG_TAG = "InternalExams";

    String internalExamUrl = "/ExaminationService.svc/GetInternalExamSchedule?FromDate=01%20Sep%202013&ToDate=30%20Nov%202013";
    String loginURL = "";
    Context ctx;
    int currentMonth = 0;
    Date initDate;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    FrameLayout progressBarHolder;
    private CredentialManager credentialManager;
    CustomCalendarView calendarView;
    Exception error;
    String from = "";
    String to = "";
    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat keyFormatter = new SimpleDateFormat("dd-MM-yyyy");
    Calendar showingCalander = Calendar.getInstance(Locale.getDefault());



    LinearLayout layoutOfPopup; LinearLayout layoutInnerOfPopup; PopupWindow popupMessage; Button popupButton, insidePopupButton; TextView popupText; TextView toText;
    DatePicker fromDatePicker; DatePicker toDatePicker;
    String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    //Initialize calendar with date
    Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());
    Calendar currentTime = Calendar.getInstance(Locale.getDefault());

    Map<Date, List<InternalExamItem>> finalData;
    Map<String, List<InternalExamItem>> finalSData = null;

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
        Intent i = new Intent(InternalExams.this, ManagementHome.class);
        startActivity(i);
        finish();
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

    public void setShowingCalander(String dateString){
        try {
            if(dateString != null) {
                Log.d(DEBUG_TAG, "setShowingCalander: " + dateString);
                this.showingCalander.setTime(formatter.parse(dateString));
            } else {
                this.showingCalander.setTime(formatter.parse(getIntent().getExtras().getString("showingCalander")));
            }
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "setShowingCalander: " + e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_internal_exams);
        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);
        ctx = this;
        setupPopUp();
        setShowingCalander(null);
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        credentialManager = new CredentialManager(ctx);
        loginURL =  credentialManager.getUniversityUrl() + "/AuthenticationService.svc/AuthenticateRequest?username="+ credentialManager.getUserName() +"&Password="+ credentialManager.getPassword();
        initDate = new Date();

        String internalExamsCache = credentialManager.getInternalExamsCache();
        from = credentialManager.getInternalExamsFromCache();
        if(isNetworkAvailable()) {
            makeNetworkCall();
        } else if(internalExamsCache != null && internalExamsCache != "") {
            Log.d(DEBUG_TAG, "invigilationDetailsCache: " + internalExamsCache);
            setTheInternalExamScreen(internalExamsCache);
            setShowingCalander(from);
            setPickers();
            calendarView.refreshCalendar(showingCalander);
            setTheTasks(fromDatePicker, toDatePicker, -1);
        }


        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToLanding();
            }
        };

        View.OnClickListener onFilterOkclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterOkclickListener");
                if (popupMessage.isShowing()) {
                    popupMessage.dismiss();
                    try {
                        Date filterMonth = formatter.parse("01-" + (fromDatePicker.getMonth() + 1) + "-" + fromDatePicker.getYear());
                        Calendar filterMonthC = Calendar.getInstance(Locale.getDefault());
                        filterMonthC.setTime(filterMonth);
                        showingCalander = filterMonthC;
                        // Make network call
                        makeNetworkCall();
                    } catch (Exception e){

                    }
                } else {
                    Toast.makeText(InternalExams.this, "From date cant exceed to date", Toast.LENGTH_SHORT).show();
                }

            }
        };







        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(true);


        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);

        //TODO: remove

        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        //progressBarHolder.setVisibility(View.VISIBLE);

        //Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date d) {
                if (finalSData != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    String ds = keyFormatter.format(cal.getTime());
                    Log.d(DEBUG_TAG, "decorate: " + ds + " " + finalSData.containsKey(ds));
                    String singleD = cal.get(Calendar.DATE) + "/";
                    if (("" + cal.get(Calendar.MONTH)).length() == 1) singleD += "0";
                    singleD += cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
                    if (finalSData != null && finalSData.containsKey(ds)) {
                        Intent i = new Intent(InternalExams.this, InternalExamSingle.class);
                        List<InternalExamItem> examItems = finalSData.get(ds);
                        Log.d(DEBUG_TAG, "onDateSelected: " + examItems.get(0).getExamID());
                        String examItemsString = new Converter().internalExamItemstoJson(examItems);
                        Log.d(DEBUG_TAG, "onDateSelected: examItemsString " + examItemsString);
                        i.putExtra("examItemsString", examItemsString);
                        i.putExtra("dateTitle", singleD);
                        i.putExtra("showingCalander", formatter.format(cal.getTime()));
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(InternalExams.this, "No Exam Scheduled on this date", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onMonthChanged(Date d) {
                if (!isNetworkAvailable()){
                    calendarView.refreshCalendar(showingCalander);
                    Toast.makeText(InternalExams.this, "Network not available.", Toast.LENGTH_SHORT).show();
                } else {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    List decorators = new ArrayList<>();
                    decorators.add(new ColorDecorator());
                    calendarView.setDecorators(decorators);
                    int m = 0;
                    if (initDate.getMonth() == 12 && d.getMonth() == 1) {
                        showingCalander.add(Calendar.MONTH, 1);
                    } else if (initDate.getMonth() > cal.get(Calendar.MONTH)) {
                        showingCalander.add(Calendar.MONTH, -1);
                    } else {
                        showingCalander.add(Calendar.MONTH, 1);
                    }
                    initDate = d;
                    calendarView.refreshCalendar(showingCalander);
                    currentMonth = showingCalander.get(Calendar.MONTH);

                    makeNetworkCall();
                }
//                Toast.makeText(InvigilationDetails.this, df.format(d), Toast.LENGTH_SHORT).show();
            }
        });



        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dateContainer);
        LayoutInflater inflater = LayoutInflater.from(this);

        View.OnClickListener onFilterclickListener = new View.OnClickListener() {
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
    }

    private void setupPopUp() {

        popupText = new TextView(this);
        insidePopupButton = new Button(this);

        layoutOfPopup = new LinearLayout(this);
        layoutInnerOfPopup = new LinearLayout(this);
        insidePopupButton.setText("OK");
        insidePopupButton.setTextColor(Color.parseColor("#FFFFFF"));
        insidePopupButton.setBackgroundColor(Color.parseColor("#03A7E9"));
        popupText.setText("Month");;
        popupText.setTextColor(Color.parseColor("#000000"));
        popupText.setTextSize(16);
        popupText.setPadding(0, 0, 0, 10);
        layoutOfPopup.setOrientation(LinearLayout.VERTICAL);
        fromDatePicker = new DatePicker(this);
        toDatePicker = new DatePicker(this);
        fromDatePicker.setCalendarViewShown(false);
        View dayId = (View)fromDatePicker.findViewById(Resources.getSystem().getIdentifier("day", "id", "android"));
        if(dayId != null) dayId.setVisibility(View.GONE);
        toDatePicker.setCalendarViewShown(false);
        layoutOfPopup.addView(popupText);
        layoutOfPopup.addView(fromDatePicker);
//        layoutOfPopup.addView(toText);
        layoutOfPopup.addView(insidePopupButton);
        layoutOfPopup.setBackgroundColor(Color.parseColor("#ffffff"));
        layoutOfPopup.setPadding(40, 40, 40, 40);
        layoutOfPopup.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        layoutInnerOfPopup.setPadding(40, 40, 40, 40);
        layoutInnerOfPopup.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        layoutInnerOfPopup.setBackgroundColor(Color.parseColor("#80000000"));
        layoutInnerOfPopup.addView(layoutOfPopup);

        popupMessage = new PopupWindow(layoutInnerOfPopup, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        popupMessage.setContentView(layoutInnerOfPopup);
    }

    private void setTheTasks(DatePicker from, DatePicker to, int tasks) {
        Log.d(DEBUG_TAG, "setTheTasks: " + from + " " + to + " " + tasks);
        if(from != null) ((TextView) findViewById(R.id.fromRange)).setText(getFormattedDate(from).replaceAll("%20","/"));
        if(to != null) ((TextView) findViewById(R.id.toRange)).setText(getFormattedDate(to).replaceAll("%20","/"));
        if(tasks != -1) ((TextView) findViewById(R.id.tasksCount)).setText("" + tasks);
    }

    private void setTheInternalExamScreen(String result) {

        try {
            currentCalendar = Calendar.getInstance(Locale.getDefault());
            JSONObject resultJSON = new JSONObject(result);
            JSONArray dataList = resultJSON.getJSONArray("DataList");
            if(dataList.length() == 0) {
                Toast.makeText(InternalExams.this, "No. Tasks in given Date range", Toast.LENGTH_SHORT).show();
                setTheTasks(null,null,0);
                finalSData = null;
                credentialManager.setInternalExamsCache("");
            } else {
                Map<String, List<InternalExamItem>> obj = new Converter().convertInternalExamsItemsString(result);
                finalSData = obj;

                currentMonth = currentCalendar.get(Calendar.MONTH);
                progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
                progressBarHolder.setVisibility(View.INVISIBLE);
                setTheTasks(null, null, dataList.length());
            }
            List decorators = new ArrayList<>();
            decorators.add(new ColorDecorator());
            calendarView.setDecorators(decorators);
            calendarView.refreshCalendar(showingCalander);

        } catch (JSONException e) {
            error = e;
//            Toast.makeText(InternalExams.this, "No Tasks in given Date range", Toast.LENGTH_SHORT).show();
            Log.d(DEBUG_TAG, "setTheInternalExamScreen: " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            error = e;
//            Toast.makeText(InternalExams.this, "No Tasks in given Date range", Toast.LENGTH_SHORT).show();
            Log.e(DEBUG_TAG, "setTheInternalExamScreen: " + e);
            Log.d(DEBUG_TAG, "setTheInternalExamScreen: " + e.getStackTrace().toString());
        }
        catch (Exception e) {
            error = e;
            Log.d(DEBUG_TAG, "setTheInternalExamScreen: " + e);
        } finally {
            new ErrorToaster().toastError(error,ctx);
            error = null;
        }
    }

    private void makeNetworkCall(){
        // Make network call
        if (isNetworkAvailable()) {
            String url = credentialManager.getUniversityUrl() + "/ExaminationService.svc/GetInternalExamSchedule?FromDate="
                    + NetworkUtils.getFormattedDate(NetworkUtils.getFirstDateOfMonth(showingCalander)) +
                    "&ToDate=" + NetworkUtils.getFormattedDate(NetworkUtils.getLastDateOfMonth(showingCalander));

            progressBarHolder.setVisibility(View.VISIBLE);
            new InternalExamsTask().execute(loginURL, url);
            setPickers();
            setTheTasks(fromDatePicker, toDatePicker, -1);
        } else {
            Toast.makeText(InternalExams.this, "Network not available.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connMgr = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) return true;
        return false;
    }

    private  void setPickers(){
        NetworkUtils.updateFromNToDatePickers(fromDatePicker, NetworkUtils.getFirstDateOfMonth(showingCalander));
        NetworkUtils.updateFromNToDatePickers(toDatePicker, NetworkUtils.getLastDateOfMonth(showingCalander));
    }

    // Network code
    private class InternalExamsTask extends AsyncTask<String, Void, String> {
        private JSONArray dataList;
        public void useLoginToken(String result){
            try {
                JSONObject resultJSON = new JSONObject(result);
                Log.d("resultJSON ", result + "");
                Intent i;
                if(resultJSON.getInt("ServiceResult") == 0) {
                    Time requestInitiatedTime = new Time();
                    GlobalData globalData = new GlobalData();
                    globalData.setLastNetworkCall(requestInitiatedTime);
                    globalData.setToken(resultJSON.getString("Token"));
                    credentialManager.setToken(resultJSON.getString("Token"));
                    Log.d(DEBUG_TAG, "The token is: " + resultJSON.getString("Token"));
                } else {
                    Log.d(DEBUG_TAG, "onPostExecute: The user is not valid ==> used: " + credentialManager.getUserName() +
                            ", password: " + credentialManager.getPassword() );
                    i = new Intent(InternalExams.this, LoginActivity.class);

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
                GlobalData globalData = new GlobalData();
                useLoginToken(downloadUrl(urls[0]));
                return downloadUrl(urls[1]);
            } catch (Exception e) {
                Log.d(DEBUG_TAG, "The response is: " + e.toString());
//                return "Unable to retrieve web page. URL may be invalid.";
                return  "yesss " + e.getMessage();
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            progressBarHolder.setVisibility(View.GONE);
            if(result.startsWith("yesss")) Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
//            credentialManager = new CredentialManager(this);
            try {
                JSONObject resultJSON = new JSONObject(result);
                if(resultJSON.getInt("ServiceResult") == 0) {
                    credentialManager.setInternalExamsCache(result);
                    credentialManager.setInternalExamsFromCache("01-" + (fromDatePicker.getMonth() + 1) + "-" + fromDatePicker.getYear());
                    setTheInternalExamScreen(result);
                    Log.d(DEBUG_TAG, "onPostExecute: from network" );
                } else {

                }
            } catch (Exception t) {
                Toast.makeText(ctx, "JSON exception" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("JSON error", t + " Could not parse malformed JSON: \"" + result + "\"");
            }
            /*Intent i = new Intent(LoginActivity.this, LandingDrawer.class);
            startActivity(i);*/
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

    private class ColorDecorator implements DayDecorator {
        @Override
        public void decorate(DayView dayView) {
            Date d = dayView.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            String ds =  keyFormatter.format(cal.getTime());
            /*Log.d(DEBUG_TAG, "decorate: " + ds +  " "+ data.containsKey(ds) + " ---- " +
                    data.keySet().toString());*/
            dayView.setBackgroundColor(getResources().getColor(R.color.grey_bg));
            if(NetworkUtils.isToday(cal)) dayView.setBackgroundColor(getResources().getColor(R.color.calenderCurrentDay));
            if(finalSData != null && finalSData.containsKey(ds)) dayView.setBackgroundColor(getResources().getColor(R.color.colorLightButton));
        }
    }
}
