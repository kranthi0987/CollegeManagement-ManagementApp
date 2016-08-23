package app.managementapp.college.com.collegemanagement.model;

import android.app.Application;
import android.text.format.Time;

/**
 * Created by new on 4/6/2016.
 */
public class GlobalData extends Application {


    private String token = "";
    private Time lastNetworkCallTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Time getLastNetworkCall() {
        return lastNetworkCallTime;
    }

    public void setLastNetworkCall(Time lastNetworkCall) {
        this.lastNetworkCallTime = lastNetworkCall;
    }

}
