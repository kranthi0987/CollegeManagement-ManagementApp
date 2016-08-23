package app.managementapp.college.com.collegemanagement.util;

import android.util.Log;
import android.widget.DatePicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by new on 4/8/2016.
 */
public class NetworkUtils {

    private static final String DEBUG_TAG = "NetworkUtils";

    private static String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};




    public static void updateFromNToDatePickers(DatePicker picker, Date cal){
        Calendar calCur = Calendar.getInstance(Locale.getDefault());
        calCur.setTime(cal);
        picker.updateDate(calCur.get(Calendar.YEAR), calCur.get(Calendar.MONTH), calCur.get(Calendar.DATE));
    }

    public static Date getFirstDateOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        Log.d(DEBUG_TAG, "getFirstDateOfCurrentMonth: " + cal.getTime());
        return cal.getTime();
    }
    public static Date getLastDateOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Log.d(DEBUG_TAG, "getLastDateOfCurrentMonth: " + cal.getTime());
        return cal.getTime();
    }


    public static Date getFirstDateOfMonth(Calendar cal) {
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        Log.d(DEBUG_TAG, "getFirstDateOfCurrentMonth: " + cal.getTime());
        return cal.getTime();
    }
    public static Date getLastDateOfMonth(Calendar cal) {
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Log.d(DEBUG_TAG, "getLastDateOfCurrentMonth: " + cal.getTime());
        return cal.getTime();
    }


    public static boolean validateDates(DatePicker fromDatePicker, DatePicker toDatePicker) {
        Log.d(DEBUG_TAG, "validateDates: " + fromDatePicker.getYear() + " < " + toDatePicker.getYear());
        if(fromDatePicker.getYear() < toDatePicker.getYear()){
            return true;
        } else if(fromDatePicker.getYear() == toDatePicker.getYear() &&
                fromDatePicker.getMonth() <= toDatePicker.getMonth() &&
                fromDatePicker.getDayOfMonth() <= toDatePicker.getDayOfMonth()) return true;
        return false;
    }

    public static String getFormattedDate(DatePicker fromDatePicker) {
        //09%20May%202014
        String ret = "";
        if(fromDatePicker.getDayOfMonth() < 10) ret += "0";
        ret += fromDatePicker.getDayOfMonth() + "%20";
        ret += months[fromDatePicker.getMonth()] + "%20";
        ret += fromDatePicker.getYear();
        return ret;
    }

    public static String getFormattedDate(Date date) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Log.d(DEBUG_TAG, "getFormattedDate: " + df.format(date));
        //09%20May%202014
        String[] rw = df.format(date).split("/");
        String rt = rw[0] + "%20" + months[Integer.parseInt(rw[1])-1] + "%20" + rw[2];
        Log.d(DEBUG_TAG, "getFormattedDate: " + rt);
        return rt;
//        return df.format(date).replace("/", "%20");
//        if(date.getMonth() < 10) ret += "0";
//        ret += date.getDay() + "%20";
//        ret += months[date.getMonth()] + "%20";
//        ret += date.getYear();
//        return ret;
    }
    public static String downloadUrl(String URI, String token) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 50000;

        try {
            URI = "http://scdemo.saas.talismaonline.com/MobileApp/service/FacultyProfileService.svc/GetFacultyProfile";

            URL url = new URL(URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Token", token);
            conn.addRequestProperty("Token", token);
            Log.d(DEBUG_TAG, "The header is: " + conn.getHeaderField("Token"));
            conn.setDoInput(true);
            // Starts the query
            try {
                conn.connect();
            } catch (Exception e) {
                conn.disconnect();
                conn.connect();
            }
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            conn.disconnect();
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "The error is: " + e.getMessage());
        }
        finally {
            if (is != null) {

                is.close();
            }
        }
        return "some error";
    }
    // Reads an InputStream and converts it to a String.
    public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
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


    public static boolean isToday(Calendar cal1) {

        Calendar cal2 = Calendar.getInstance(Locale.getDefault());
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}
