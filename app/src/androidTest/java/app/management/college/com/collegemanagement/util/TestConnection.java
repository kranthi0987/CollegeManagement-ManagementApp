package app.managementapp.college.com.collegemanagement.util;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by new on 4/8/2016.
 */
public class TestConnection {
    private static final String DEBUG_TAG = "TestConnection";

    public static void main(String args[]){
        System.out.print("ok");
        try {
            String URI = "";

            URL url = new URL(URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* millisecondhttp://scdemo.saas.talismaonline.com/MobileApp/service/AccademicService.svc/GetClassSchedule?StartDate=01 Apr 2016&EndDate=30 Apr 2016s */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Token", token);
//            conn.addRequestProperty("Token", token);
            Log.d(DEBUG_TAG, "The header is: " + conn.getHeaderField("Token"));
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
        } catch (Exception e){
            Log.d(DEBUG_TAG, e.getMessage());
        }

    }
}
