package app.managementapp.college.com.collegemanagement;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import app.managementapp.college.com.collegemanagement.management.ManagementHome;
import app.managementapp.college.com.collegemanagement.model.GlobalData;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;
import app.managementapp.college.com.collegemanagement.util.ErrorToaster;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {


    /**
     *
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "Amberker", "19740315", "http://scdemo.saas.talismaonline.com/MobileApp/service"
    };
    private static final String DEBUG_TAG = "Network";

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mURLView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView textView;
    private Time requestInitiatedTime;
    private CredentialManager credentialManager = new CredentialManager(this);
    String userName;
    String password;
    String universityUrl;
    String url;
    String autoLogin;
    Context ctx;
    boolean urlRequired = true;
    Exception error = null;
    boolean firstExit = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        textView = (TextView) findViewById(R.id.test);
        mPasswordView = (EditText) findViewById(R.id.password);
        mURLView = (EditText) findViewById(R.id.url);

        String universityBase64 = null;
        ctx = this;
        try {
            universityBase64 = credentialManager.getUniversityLogo();
        } catch (Exception e) {

        }
        if(universityBase64 != null && universityBase64.length() > 0) setUniversityLogoInScreen(universityBase64);
        getSupportActionBar().hide();

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                /*if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }*/
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        testing();
    }

    private  void fillRememberedUser(){
        Log.d(DEBUG_TAG, "fillRememberedUser: " + credentialManager.getUserName() + " " + credentialManager.getPassword());
        mEmailView.setText(credentialManager.getUserName());
        mPasswordView.setText(credentialManager.getPassword());
//        mURLView.setText(DUMMY_CREDENTIALS[2]);
    }

    private void autoLogin() {

    }
    private void testing() {
        mEmailView.setText(DUMMY_CREDENTIALS[0]);
        mPasswordView.setText(DUMMY_CREDENTIALS[1]);
        mURLView.setText(DUMMY_CREDENTIALS[2]);
    }

    public void onBackPressed (){
        Log.d("onBackPressed", "onBackPressed: ");
        if(firstExit) {
            firstExit = false;
            Toast.makeText(ctx, "Press again to exit", Toast.LENGTH_LONG).show();
            return;
        } else {
            firstExit = true;
            this.moveTaskToBack(true);
        }
        this.moveTaskToBack(true);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        userName = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        url = mURLView.getText().toString();
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        universityUrl = url;
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(userName)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (userName.contains(" ")) {
            mEmailView.setError(getString(R.string.error_invalid_username));
            focusView = mEmailView;
            cancel = true;
        } else if (!isUserValid(userName)) {
            mEmailView.setError(getString(R.string.error_user_length));
            focusView = mEmailView;
            cancel = true;
        } else if (urlRequired && TextUtils.isEmpty(url)) {
            mURLView.setError(getString(R.string.error_url_empty));
            focusView = mURLView;
            cancel = true;
        }

        if(!urlRequired) {
            universityUrl = credentialManager.getUniversityUrl();
//            Toast.makeText(ctx, "Saved Univ URL: " + universityUrl, Toast.LENGTH_LONG).show();
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);

            String stringUrl = universityUrl + "/authenticationService.svc/AuthenticateRequestForFirstLogin?username="+ userName +"&Password="+ password;
//            stringUrl = stringUrl.replace("scdemo.saas.talismaonline.com/MobileApp/service", url);
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new LoginTask().execute(stringUrl);
            } else {
//                textView.setText("No network connection available.");
                Toast.makeText(ctx, "No network connection available.", Toast.LENGTH_LONG).show();
            }

        }
    }

    private boolean isUserValid(String user) {
        return user.length() > 4;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }



    // Network code
    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                Log.d(DEBUG_TAG, "doInBackground: " + urls[0]);
                return downloadUrl(urls[0]);
            } catch (UnknownHostException e) {
                error = e;
                Log.d(DEBUG_TAG, "The response is: " + e.toString());
                return "UnknownHostException";
            }catch (MalformedURLException e) {
                error = e;
                Log.d(DEBUG_TAG, "The response is: " + e.toString());
                return "MalformedURLException";

            }catch (FileNotFoundException e) {
                error = e;
                Log.d(DEBUG_TAG, "The response is: " + e.toString());
                return "FileNotFoundException";

            }catch (Exception e) {
                error = e;
                Log.d(DEBUG_TAG, "The response is: " + e.toString());
                return "ERROR: " + e.getMessage();
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {


            if(result.equals("UnknownHostException") || result.equals("MalformedURLException") ||
                    result.equals("FileNotFoundException")){
                Toast.makeText(ctx, "University URL incorrect", Toast.LENGTH_LONG).show();
            } else if(result.startsWith("ERROR:")){
                Toast.makeText(ctx, "Something went wrong", Toast.LENGTH_LONG).show();
            } else {

                //            credentialManager = new CredentialManager(this);
                try {
                    JSONObject resultJSON = new JSONObject(result);
                    Log.e("resultJSON ", result + "");
                    if (resultJSON.getInt("ServiceResult") == 0) {
                        //if(credentialManager.isFirstTimeUser() || credentialManager.isLoggedOutUser()){
                        if (true) {
                            // success
                            // Set the token and time for future use.
                            requestInitiatedTime = new Time();
                            GlobalData globalData = new GlobalData();
                            globalData.setLastNetworkCall(requestInitiatedTime);
                            globalData.setToken(resultJSON.getString("ExtendedToken"));
                            Log.d(DEBUG_TAG, "The token is: " + resultJSON.getString("ExtendedToken"));
                            JSONArray dataListArry = resultJSON.getJSONArray("DataList");
                            JSONObject dataList = (JSONObject) dataListArry.get(0);
                            setUniversityLogoInScreen(dataList.getString("Base64Image"));
                            credentialManager.storeUser(userName, password, ((CheckBox) findViewById(R.id.rememberMe)).isChecked());
                            credentialManager.setUniversityUrl(universityUrl);
                            credentialManager.setUniversityLogo(dataList.getString("Base64Image"));
                            credentialManager.setToken(resultJSON.getString("ExtendedToken"));
                            credentialManager.updateStatus(true);
                            // user logged in and moving to next screen
                            Intent i = new Intent(LoginActivity.this, ManagementHome.class);
                            startActivity(i);

                        } else {

                        }
                    } else {
                        mEmailView.setError(getString(R.string.error_invalid_credentials));
                        Toast.makeText(ctx, "Username/Password incorrect", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception t) {
                    Toast.makeText(ctx, "JSON exception" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("JSON error", t + " Could not parse malformed JSON: \"" + result + "\"");
                }
            }
            if(new ErrorToaster().toastError(error,ctx)) {
                error = null;
                return;
            }
            /*Intent i = new Intent(LoginActivity.this, LandingDrawer.class);
            startActivity(i);*/
        }
    }

    private void setUniversityLogoInScreen(String base64Image) {

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        ImageView universityImage = (ImageView) findViewById(R.id.universityLogo);
        universityImage.setImageBitmap(decodedByte);

        // hide url
        ((TextInputLayout) findViewById(R.id.urlCon)).setVisibility(View.GONE);
        // fill if the user is remembered
        if(credentialManager.isRememberedUser()) fillRememberedUser();
        urlRequired = false;
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
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
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

