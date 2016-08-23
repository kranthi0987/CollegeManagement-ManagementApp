package app.managementapp.college.com.collegemanagement;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

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
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.managementapp.college.com.collegemanagement.management.ManagementHome;
import app.managementapp.college.com.collegemanagement.model.ClassData;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;

public class TimeTable extends FragmentActivity {

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
    CredentialManager credentialManager;

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
        Intent i = new Intent(TimeTable.this, ManagementHome.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_time_table);

        credentialManager = new CredentialManager(this);
        data = getData();
        initDatesUI(data);

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
        View.OnClickListener onFilterOkclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterOkclickListener");
                if (popupMessage.isShowing()) {
                    if(validateDates()) {
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
//                            url = credentialManager.frameUniversityUrl(url);
                            new TimeTableTask().execute(url);
                        } else {
                            //"No network connection available.");
                        }
                    }

                }
            }
        };

        View.OnClickListener onFilterbackTimeTableclickListener = new View.OnClickListener() {
            public static final String DEBUG_TAG = "TimeTable";
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
                moveToLanding();

            }
        };

        ImageView filter = (ImageView)findViewById(R.id.filter);
//        popupMessage.showAsDropDown(insidePopupButton, 0, 0);

        filter.setOnClickListener(onFilterclickListener);
        ImageView backTimeTable = (ImageView)findViewById(R.id.backTimeTable);
//        popupMessage.showAsDropDown(insidePopupButton, 0, 0);

        backTimeTable.setOnClickListener(onFilterbackTimeTableclickListener);
        insidePopupButton.setOnClickListener(onFilterOkclickListener);
    }

    private void initDatesUI(Map<String, List<ClassData>> data) {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dateContainer);
        LayoutInflater inflater = LayoutInflater.from(this);
        Iterator<String> it = data.keySet().iterator();
        boolean done = true;
        while (it.hasNext()){
            String key = it.next();

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/mm/yyyy");
            try {
                Date date = inputFormat.parse(key);
                Log.d(DEBUG_TAG, "Date: " + date.toString());
                View view  = inflater.inflate(R.layout.row_time_table_date_item_vertical, linearLayout, false);
                // set item content in view
                linearLayout.addView(view);
//                ImageButton highligh = (ImageButton) view.findViewById(R.id.highlight);
//                highligh.setVisibility(View.INVISIBLE);
                if(done){
                    done = false;
                    makeDateActive(view);
//                    ((LinearLayout) view).setBackgroundColor(Color.parseColor("#EBEFF0"));
//                    lastAcitveDateRipple = (LinearLayout) view;
//                    highligh.setVisibility(View.VISIBLE);
//                    lastAcitveDateImage = highligh;
                    List<ClassData> classDataList = data.get(key);
                    initClasses(classDataList);
                }
                TextView dayText = (TextView) view.findViewById(R.id.dayText);
                dayText.setText(date.getDate() + "");
                TextView monthText = (TextView) view.findViewById(R.id.monthText);
                monthText.setText(months[date.getMonth()]);
//                TextView weekDayText = (TextView) view.findViewById(R.id.weekDayText);
//                weekDayText.setText(date.toString().substring(0,3));

                ((MaterialRippleLayout) view.findViewById(R.id.lyt_parent)).setOnClickListener(new DateOnclickListener(key));
                views.add(view);

            } catch (Exception e){
                Log.d(DEBUG_TAG, "initDatesUI: " + e.toString());
            }
        }
    }

    public boolean removeAnimated(LinearLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            Log.d(DEBUG_TAG, "removing: " + i + "  of " +layout.getChildCount());
            final View v = layout.getChildAt(i);
            final ViewGroup viewGroup = ((ViewGroup) v.getParent());
//            viewGroup.removeView(v);
            v.animate()
                    .translationX(-v.getWidth())
                    .alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            v.setVisibility(View.GONE);
                            viewGroup.removeView(v);
                        }
                    });
        }
        return true;
    }

    private void initClasses(List<ClassData> classDataList) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.classItemsCont);
        LayoutInflater inflater = LayoutInflater.from(this);
        removeAnimated(linearLayout);
        for (ClassData classData: classDataList ) {
            View view  = inflater.inflate(R.layout.row_time_table_class_item, linearLayout, false);

            ((TextView) view.findViewById(R.id.courseDesc)).setText("Course: " + classData.getCourse());
            ((TextView) view.findViewById(R.id.subject)).setText(classData.getTitle());
            ((TextView) view.findViewById(R.id.location)).setText(classData.getSection() + ", " + classData.getPlace());
            ((TextView) view.findViewById(R.id.time1)).setText(classData.getStartTime());
            ((TextView) view.findViewById(R.id.time2)).setText(classData.getEndTime());
            view.setOnClickListener(new ClassOnclickListener());
            linearLayout.addView(view);
        }

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


    // Network code
    private class TimeTableTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
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
                /*Log.d("resultJSON ", result + "");
                if(resultJSON.getInt("ServiceResult") == 0) {
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
//    View.OnClickListener onclickListener = new View.OnClickListener() {
public class  DateOnclickListener  implements View.OnClickListener {
    public static final String DEBUG_TAG = "TimeTable";
    String key;
    public DateOnclickListener(String key) {
        this.key = key;
    }
    @Override
    public void onClick(View v) {
        Log.d(DEBUG_TAG, "onClick: key is -> " + key);
        List<ClassData> classDataList = data.get(key);
//            if(lastAcitveDateRipple != null) lastAcitveDateRipple.setBackgroundColor(Color.parseColor("#179bd7"));
//            lastAcitveDateRipple = (LinearLayout) v;
        makeDateActive(v);
//            if(lastAcitveDateImage != null) lastAcitveDateImage.setVisibility(View.INVISIBLE);
//            ImageButton currentActiveDateImage = (ImageButton) v.findViewById(R.id.highlight);
//            currentActiveDateImage.setVisibility(View.VISIBLE);
//            lastAcitveDateImage = currentActiveDateImage;
        initClasses(classDataList);
    }
}
    public class  ClassOnclickListener  implements View.OnClickListener {
        public static final String DEBUG_TAG = "TimeTable";
        public ClassOnclickListener() {
        }
        @Override
        public void onClick(View v) {
            Intent i = new Intent(TimeTable.this, AttendanceForClass.class);
            startActivity(i);
            finish();
        }
    }

    public void makeDateActive(View v) {
        if(lastAcitveDateRipple != null) lastAcitveDateRipple.setBackgroundColor(0x00000000);
        if(lastMonth != null) lastMonth.setTextColor(Color.parseColor("#ffffff"));
        if(lastDate != null) lastDate.setTextColor(Color.parseColor("#ffffff"));
        lastAcitveDateRipple = (LinearLayout) v;
        lastMonth = (TextView) v.findViewById(R.id.monthText);
        lastDate = (TextView) v.findViewById(R.id.dayText);
        v.setBackgroundColor(Color.parseColor("#EBEFF0"));
        lastMonth.setTextColor(Color.parseColor("#179bd7"));
        lastDate.setTextColor(Color.parseColor("#179bd7"));
    }

    public class  AttendanceOnclickListener  implements View.OnClickListener {
        String classScheduleID;
        public AttendanceOnclickListener(String classScheduleID) {
            this.classScheduleID = classScheduleID;
        }
        @Override
        public void onClick(View v) {

        }
    }


    ;
}
