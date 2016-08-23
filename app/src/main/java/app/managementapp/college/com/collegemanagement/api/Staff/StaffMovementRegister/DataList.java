
package app.managementapp.college.com.collegemanagement.api.Staff.StaffMovementRegister;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Time")
    @Expose
    private String time;

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The Date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The time
     */
    public String getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The Time
     */
    public void setTime(String time) {
        this.time = time;
    }

}
