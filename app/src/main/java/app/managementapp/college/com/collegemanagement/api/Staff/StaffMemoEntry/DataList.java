
package app.managementapp.college.com.collegemanagement.api.Staff.StaffMemoEntry;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("Action")
    @Expose
    private String action;
    @SerializedName("Activity")
    @Expose
    private String activity;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("MemoDate")
    @Expose
    private String memoDate;
    @SerializedName("ReportedBy")
    @Expose
    private String reportedBy;

    /**
     * 
     * @return
     *     The action
     */
    public String getAction() {
        return action;
    }

    /**
     * 
     * @param action
     *     The Action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 
     * @return
     *     The activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * 
     * @param activity
     *     The Activity
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The memoDate
     */
    public String getMemoDate() {
        return memoDate;
    }

    /**
     * 
     * @param memoDate
     *     The MemoDate
     */
    public void setMemoDate(String memoDate) {
        this.memoDate = memoDate;
    }

    /**
     * 
     * @return
     *     The reportedBy
     */
    public String getReportedBy() {
        return reportedBy;
    }

    /**
     * 
     * @param reportedBy
     *     The ReportedBy
     */
    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

}
