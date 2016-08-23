package app.managementapp.college.com.collegemanagement;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import app.managementapp.college.com.collegemanagement.management.ManagementHome;
import app.managementapp.college.com.collegemanagement.model.GlobalData;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;
import app.managementapp.college.com.collegemanagement.util.ErrorToaster;

/**
 * Created by yyeshwanth4 on 3/17/2016.
 */
public class ActivitySplash extends AppCompatActivity {

    private static final String DEBUG_TAG = "ActivitySplash";
    private String loginURL;
    Exception error = null;
    Context ctx;
    int response = 0;

    private CredentialManager credentialManager = new CredentialManager(ActivitySplash.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bindLogo();
        ctx = this;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // test wether storage has credentials
                // if so try to login.
                if (!credentialManager.isFirstTimeUser()) {
                    Log.d(DEBUG_TAG, "Not first time user");
                    // try to login.
                    autoLogin();
                } else {
                    // If he is first time user.
                    Log.d(DEBUG_TAG, "first time user");
                    Intent i = new Intent(ActivitySplash.this, LoginActivity.class);
                    startActivity(i);
                    // kill current activity.
                    finish();
                }

            }

            private void autoLogin() {
                loginURL = credentialManager.getUniversityUrl() +
                        "/AuthenticationService.svc/AuthenticateRequest?username="+ credentialManager.getUserName()
                        +"&Password="+ credentialManager.getPassword();
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new LoginTask().execute(loginURL);
                } else {
                    //TODO: rethink
                    Log.d(DEBUG_TAG, "autoLogin: No network so moving to Landing to show earlier data");
                    Intent i = new Intent(ActivitySplash.this, ManagementHome.class);
                    startActivity(i);
                    finish();
                }

            }
        };
        // Show splash screen for 3 seconds
        new Timer().schedule(task, 3000);
    }

    // Animation for the product logo
    private void bindLogo(){
        // Start animating the image
        final ImageView splash = (ImageView) findViewById(R.id.splash);
        final AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(700);
        final AlphaAnimation animation2 = new AlphaAnimation(1.0f, 0.2f);
        animation2.setDuration(700);
        //animation1 AnimationListener
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                // start animation2 when animation1 ends (continue)
                splash.startAnimation(animation2);
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {}
            @Override
            public void onAnimationStart(Animation arg0) {}
        });

        //animation2 AnimationListener
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                // start animation1 when animation2 ends (repeat)
                splash.startAnimation(animation1);
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {}
            @Override
            public void onAnimationStart(Animation arg0) {}
        });

        splash.startAnimation(animation1);
    }




    // Network code
    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            } catch (Exception e) {
                error = e;
                return "Unable to retrieve web page. URL may be invalid. " + urls[0];
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            if(response == 404) Toast.makeText(ctx, "response is 404", Toast.LENGTH_LONG).show();
            if(new ErrorToaster().toastError(error,ctx)) {
                Log.d(DEBUG_TAG, "onPostExecute: its here 1" );
                error = null;
                return;
            }

            try {
                JSONObject resultJSON = new JSONObject(result);
                Log.e("resultJSON ", result + "");
                Intent i;
                if(resultJSON.getInt("ServiceResult") == 0) {
                    i = new Intent(ActivitySplash.this, ManagementHome.class);
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
                    i = new Intent(ActivitySplash.this, LoginActivity.class);
                }
                startActivity(i);
                // kill current activity
                finish();
            } catch (Throwable t) {
                Toast.makeText(ctx, "JSON exception" + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("JSON error", t.getMessage() + " Could not parse malformed JSON: \"" + result + "\"");
            }
            if(new ErrorToaster().toastError(error,ctx)) {
                Log.d(DEBUG_TAG, "onPostExecute: its here 2" );
                error = null;
                return;
            }
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
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
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