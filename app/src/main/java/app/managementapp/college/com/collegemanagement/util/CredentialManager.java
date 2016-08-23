package app.managementapp.college.com.collegemanagement.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Yeshwanth on 4/1/2016.
 * To store and retrieve the credentials from shared preferences
 * and to find wether the credentials are already existing
 */
public class CredentialManager {
    String userName;
    String password;
    String rememberMe;
    String loggedStatus;
    String token;
    String universityUrl;
    String universityLogo;
    Context context;

    String newsCache = "";

    String profileCache = "";
    String profileFromCache = "";
    String profileToCache = "";

    String invigilationDetailsCache = "";
    String invigilationFromCache = "";



    String externalExamsCache = "";
    String externalExamsFromCache = "";



    String internalExamsCache = "";
    String internalExamsFromCache = "";
    String internalMarksUpdateCache = "";


    String timeTableCache = "";
    String timeTableFromCache = "";
    String timeTableUpdateCache = "";


    final String TAG = "CredentialManager";

    public CredentialManager(Context context){
        this.context = context;
    }


    public void clearAllCache(){
        String blank = "";

        profileCache = blank;
        profileFromCache = blank;
        profileToCache = blank;

        invigilationDetailsCache = blank;
        invigilationFromCache = blank;

        externalExamsCache = blank;
        externalExamsFromCache = blank;

        internalExamsCache = blank;
        internalExamsFromCache = blank;
        internalMarksUpdateCache = blank;

        timeTableCache = blank;
        timeTableFromCache = blank;
        timeTableUpdateCache = blank;

        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("profileCache", profileCache);
            editor.putString("profileFromCache", profileFromCache);
            editor.putString("profileToCache", profileToCache);
            editor.putString("invigilationDetailsCache", invigilationDetailsCache);
            editor.putString("invigilationFromCache", invigilationFromCache);
            editor.putString("externalExamsCache", externalExamsCache);
            editor.putString("externalExamsFromCache", externalExamsFromCache);
            editor.putString("internalExamsCache", internalExamsCache);
            editor.putString("internalExamsFromCache", internalExamsFromCache);
            editor.putString("internalMarksUpdateCache", internalMarksUpdateCache);
            editor.putString("timeTableCache", timeTableCache);
            editor.putString("timeTableFromCache", timeTableFromCache);
            editor.putString("timeTableUpdateCache", timeTableUpdateCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }

    }
    public boolean updateStatus(boolean loggedStatus){
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            if(loggedStatus) {
                editor.putString("loggedStatus","true");
            } else {
                editor.putString("loggedStatus","false");
            }
            editor.commit();
            this.loggedStatus = loggedStatus + "";
        } catch (Exception e){
            Log.e("error", e.toString());
            return false;
        }
        return true;
    }


    public boolean storeUser(String userName, String password, boolean rememberMe){
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("Username",userName);
            editor.putString("Password",password);
            if(rememberMe) {
                editor.putString("RememberMe","true");
            } else {
                editor.putString("RememberMe","false");
            }
            editor.commit();
            this.userName = userName;
            this.password = password;
        } catch (Exception e){

            Log.e("error", e.toString());
            return false;
        }

        return true;
    }


    public boolean getStoredUser() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        userName = settings.getString("Username", "").toString();
        password = settings.getString("Password", "").toString();
        rememberMe = settings.getString("RememberMe", "").toString();
        loggedStatus = settings.getString("loggedStatus", "").toString();
        Log.d(TAG, "getStoredUser: " + userName + " " + password + " " + rememberMe + " " + loggedStatus);
        return true;
    }

    public String getUserName(){
        if(userName == null && !getStoredUser()) {
            return "";
        } else {
            return userName;
        }
    }

    public String getPassword(){
        if(password == null && !getStoredUser()) {
            return "";
        } else {
            return password;
        }
    }

    // This handles both first time & logged out user
    public boolean isFirstTimeUser() {
        getStoredUser();
        Log.d(TAG, "isFirstTimeUser: " + loggedStatus);
        if(loggedStatus == "" || loggedStatus.equals("false")) return true;
        if((userName == null || userName.length() == 0) &&
                (password == null  || password.length() == 0)
                ) {
            return true;
        } else{
            return false;
        }
    }

    public boolean isRememberedUser() {
        if(rememberMe == null) getStoredUser();
        if(rememberMe.equals("true")) return true;
        return false;
    }



    // This handles both first time & logged out user
    public boolean isLoggedOutUser() {
        getStoredUser();
        if(userName != null && password == null ) {
            return true;
        } else{
            return false;
        }
    }

    public String getUniversityLogo() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        return settings.getString("universityLogo", "").toString();
    }

    public boolean setUniversityLogo(String universityLogo) {
        this.universityLogo = universityLogo;
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("universityLogo", universityLogo);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.getMessage());
            return false;
        }

        return true;
    }

    public String getToken() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        token = settings.getString("token", "").toString();

        return token;
    }

    public void setUniversityUrl(String universityUrl) {
        this.universityUrl = universityUrl;
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("universityUrl", universityUrl);
            editor.commit();
            Log.d(TAG, "setUniversityUrl: " + universityUrl);
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }


    public String getUniversityUrl() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        universityUrl = settings.getString("universityUrl", "").toString();
        Log.d(TAG, "getUniversityUrl: " + universityUrl);
        return universityUrl;
//        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
//        return settings.getString("universityUrl", "").toString();
    }

    /*public String frameUniversityUrl(String url) {
        return url.replace("scdemo.saas.talismaonline.com/MobileApp/service", getUniversityUrl());
    }*/

    public void setToken(String token) {
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("token", token);
            editor.commit();
            this.token = token;
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }

    // PROFILE
    public String getProfileCache() {
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            profileCache = settings.getString("profileCache", "").toString();
            Log.d(TAG, "getProfileCache: " + profileCache);
        } catch (Exception e) {
            Log.e(TAG, "getProfileCache: " + e.getMessage());
        }
        return profileCache;
    }

    public void setProfileCache(String profileCache) {
        this.profileCache = profileCache;
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("profileCache", profileCache);
            editor.commit();
            this.profileCache = profileCache;
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }

    // NEWS
    public String getNewsCache() {
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            profileCache = settings.getString("newsCache", "").toString();
            Log.d(TAG, "newsCache: " + newsCache);
        } catch (Exception e) {
            Log.e(TAG, "newsCache: " + e.getMessage());
        }
        return profileCache;
    }

    public void setNewsCache(String newsCache) {
        this.newsCache = newsCache;
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("newsCache", newsCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }



    public String getProfileToCache() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("profileToCache ", settings.getString("profileToCache", "").toString());
        return settings.getString("profileToCache", "").toString();
    }

    public void setProfileToCache(String profileToCache) {
        this.profileToCache = profileToCache;
        try {
            Log.d("profileToCache ", profileToCache);
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("profileToCache", profileToCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }


    public String getProfileFromCache() {
        return profileFromCache;
    }

    public void setProfileFromCache(String profileFromCache) {
        this.profileFromCache = profileFromCache;
        try {
            Log.d("profileFromCache ", profileFromCache);
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("profileFromCache", profileFromCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }




    public String getInvigilationFromCache() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("invigilationFromCache ", settings.getString("invigilationFromCache", "").toString());
        return settings.getString("invigilationFromCache", "").toString();
    }

    public void setInvigilationFromCache(String invigilationFromCache) {
        this.invigilationFromCache = invigilationFromCache;
        try {
            Log.d("invigilationFromCache ", invigilationFromCache);
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("invigilationFromCache", invigilationFromCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }



    public String getInvigilationDetailsCache() {
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            invigilationDetailsCache = settings.getString("invigilationDetailsCache", "").toString();
            Log.d(TAG, "invigilationDetailsCache: " + invigilationDetailsCache);
        } catch (Exception e) {
            Log.e(TAG, "invigilationDetailsCache: " + e.getMessage());
        }
        return invigilationDetailsCache;
    }

    public void setInvigilationDetailsCache(String invigilationDetailsCache) {
        this.invigilationDetailsCache = invigilationDetailsCache;
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("invigilationDetailsCache", invigilationDetailsCache);
            editor.commit();
            this.invigilationDetailsCache = invigilationDetailsCache;
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }

    public String getExternalExamsCache() {

        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("getExternalExamsCache ", settings.getString("externalExamsCache", "").toString());
        return settings.getString("externalExamsCache", "").toString();
    }

    public void setExternalExamsCache(String externalExamsCache) {
        this.externalExamsCache = externalExamsCache;
        try {
            Log.e("setExternalExamsCache ", externalExamsCache + "");
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("externalExamsCache", externalExamsCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }


    public String getExternalExamsFromCache() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("externalExamsFromCache ", settings.getString("externalExamsFromCache", "").toString());
        return settings.getString("externalExamsFromCache", "").toString();
    }

    public void setExternalExamsFromCache(String externalExamsFromCache) {
        this.externalExamsFromCache = externalExamsFromCache;
        try {
            Log.e("setExternalExamsFromC", externalExamsFromCache + "");
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("externalExamsFromCache", externalExamsFromCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }

    public String getInternalExamsCache() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("getInternalExamsCache ", settings.getString("internalExamsCache", "").toString());
        return settings.getString("internalExamsCache", "").toString();
    }

    public void setInternalExamsCache(String internalExamsCache) {
        this.internalExamsCache = internalExamsCache;
        try {
            Log.e("setInternalExamsCache ", internalExamsCache + "");
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("internalExamsCache", internalExamsCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }

    public String getInternalExamsFromCache() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("internalExamsFromCache ", settings.getString("internalExamsFromCache", "").toString());
        return settings.getString("internalExamsFromCache", "").toString();
    }

    public void setInternalExamsFromCache(String internalExamsFromCache) {
        this.internalExamsFromCache = internalExamsFromCache;
        try {
            Log.d("internalExamsFromCache ", internalExamsFromCache);
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("internalExamsFromCache", internalExamsFromCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }

    public String getInternalMarksUpdateCache() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("internalMarksUpdateCach", settings.getString("internalMarksUpdateCache", "").toString());
        return settings.getString("internalMarksUpdateCache", "").toString();
    }

    public void setInternalMarksUpdateCache(String internalMarksUpdateCache) {
        this.internalMarksUpdateCache = internalMarksUpdateCache;
        try {
            Log.d("internalMarksUpdateCach", internalMarksUpdateCache);
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("internalMarksUpdateCache", internalMarksUpdateCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }



    // timetable
    public String getTimeTableCache() {
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            timeTableCache = settings.getString("timeTableCache", "").toString();
            Log.d(TAG, "getTimeTableCache: " + timeTableCache);
        } catch (Exception e) {
            Log.e(TAG, "getTimeTableCache: " + e.getMessage());
        }
        return timeTableCache;
    }

    public void setTimeTableCache(String timeTableCache) {
        this.timeTableCache = timeTableCache;
        try {
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("timeTableCache", timeTableCache);
            editor.commit();
            this.timeTableCache = timeTableCache;
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }



    public String getTimeTableFromCache() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("timeTableFromCache", settings.getString("timeTableFromCache", "").toString());
        return settings.getString("timeTableFromCache", "").toString();
    }

    public void setTimeTableFromCache(String timeTableFromCache) {
        this.timeTableFromCache = timeTableFromCache;
        try {
            Log.d("timeTableFromCache", timeTableFromCache);
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("timeTableFromCache", timeTableFromCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }


    public String getTimeTableUpdateCache() {
        SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
        Log.d("timeTableUpdateCache", settings.getString("timeTableUpdateCache", "").toString());
        return settings.getString("timeTableUpdateCache", "").toString();
    }

    public void setTimeTableUpdateCache(String timeTableUpdateCache) {
        this.timeTableUpdateCache = timeTableUpdateCache;
        try {
            Log.d("timeTableUpdateCache", timeTableUpdateCache);
            SharedPreferences settings = context.getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("timeTableUpdateCache", timeTableUpdateCache);
            editor.commit();
        } catch (Exception e){
            Log.e("error", e.toString());
        }
    }
}
