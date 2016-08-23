
package app.managementapp.college.com.collegemanagement.api.Staff.StaffLeavesApplied;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AlternateStaffStatusDetail {

    @SerializedName("AlternateStaffName")
    @Expose
    private Object alternateStaffName;
    @SerializedName("Comment")
    @Expose
    private String comment;
    @SerializedName("GUID")
    @Expose
    private String gUID;
    @SerializedName("IsModified")
    @Expose
    private Boolean isModified;
    @SerializedName("ScheduleID")
    @Expose
    private Integer scheduleID;
    @SerializedName("Status")
    @Expose
    private Integer status;

    /**
     * 
     * @return
     *     The alternateStaffName
     */
    public Object getAlternateStaffName() {
        return alternateStaffName;
    }

    /**
     * 
     * @param alternateStaffName
     *     The AlternateStaffName
     */
    public void setAlternateStaffName(Object alternateStaffName) {
        this.alternateStaffName = alternateStaffName;
    }

    /**
     * 
     * @return
     *     The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 
     * @param comment
     *     The Comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 
     * @return
     *     The gUID
     */
    public String getGUID() {
        return gUID;
    }

    /**
     * 
     * @param gUID
     *     The GUID
     */
    public void setGUID(String gUID) {
        this.gUID = gUID;
    }

    /**
     * 
     * @return
     *     The isModified
     */
    public Boolean getIsModified() {
        return isModified;
    }

    /**
     * 
     * @param isModified
     *     The IsModified
     */
    public void setIsModified(Boolean isModified) {
        this.isModified = isModified;
    }

    /**
     * 
     * @return
     *     The scheduleID
     */
    public Integer getScheduleID() {
        return scheduleID;
    }

    /**
     * 
     * @param scheduleID
     *     The ScheduleID
     */
    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The Status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}
