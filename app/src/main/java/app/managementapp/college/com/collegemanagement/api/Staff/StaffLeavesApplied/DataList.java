
package app.managementapp.college.com.collegemanagement.api.Staff.StaffLeavesApplied;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("AvailableLeaves")
    @Expose
    private Integer availableLeaves;
    @SerializedName("IsHalfdayAllowed")
    @Expose
    private Boolean isHalfdayAllowed;
    @SerializedName("LeaveID")
    @Expose
    private Integer leaveID;
    @SerializedName("LeaveName")
    @Expose
    private String leaveName;
    @SerializedName("MaximumNoOfDays")
    @Expose
    private Integer maximumNoOfDays;
    @SerializedName("MinimumNoOfDays")
    @Expose
    private Integer minimumNoOfDays;
    @SerializedName("ShortName")
    @Expose
    private String shortName;
    @SerializedName("AlternateStaffStatusDetails")
    @Expose
    private List<AlternateStaffStatusDetail> alternateStaffStatusDetails = new ArrayList<AlternateStaffStatusDetail>();
    @SerializedName("ApplicationID")
    @Expose
    private Integer applicationID;
    @SerializedName("AppliedByID")
    @Expose
    private Integer appliedByID;
    @SerializedName("AppliedByName")
    @Expose
    private Object appliedByName;
    @SerializedName("ApprovalStatus")
    @Expose
    private Integer approvalStatus;
    @SerializedName("Comment")
    @Expose
    private String comment;
    @SerializedName("LeaveAppliedDate")
    @Expose
    private String leaveAppliedDate;
    @SerializedName("LeaveDateFrom")
    @Expose
    private String leaveDateFrom;
    @SerializedName("LeaveDateTo")
    @Expose
    private String leaveDateTo;
    @SerializedName("LeaveRequestSentTo")
    @Expose
    private String leaveRequestSentTo;
    @SerializedName("LeaveStatusID")
    @Expose
    private Integer leaveStatusID;
    @SerializedName("Reason")
    @Expose
    private String reason;

    /**
     * 
     * @return
     *     The availableLeaves
     */
    public Integer getAvailableLeaves() {
        return availableLeaves;
    }

    /**
     * 
     * @param availableLeaves
     *     The AvailableLeaves
     */
    public void setAvailableLeaves(Integer availableLeaves) {
        this.availableLeaves = availableLeaves;
    }

    /**
     * 
     * @return
     *     The isHalfdayAllowed
     */
    public Boolean getIsHalfdayAllowed() {
        return isHalfdayAllowed;
    }

    /**
     * 
     * @param isHalfdayAllowed
     *     The IsHalfdayAllowed
     */
    public void setIsHalfdayAllowed(Boolean isHalfdayAllowed) {
        this.isHalfdayAllowed = isHalfdayAllowed;
    }

    /**
     * 
     * @return
     *     The leaveID
     */
    public Integer getLeaveID() {
        return leaveID;
    }

    /**
     * 
     * @param leaveID
     *     The LeaveID
     */
    public void setLeaveID(Integer leaveID) {
        this.leaveID = leaveID;
    }

    /**
     * 
     * @return
     *     The leaveName
     */
    public String getLeaveName() {
        return leaveName;
    }

    /**
     * 
     * @param leaveName
     *     The LeaveName
     */
    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    /**
     * 
     * @return
     *     The maximumNoOfDays
     */
    public Integer getMaximumNoOfDays() {
        return maximumNoOfDays;
    }

    /**
     * 
     * @param maximumNoOfDays
     *     The MaximumNoOfDays
     */
    public void setMaximumNoOfDays(Integer maximumNoOfDays) {
        this.maximumNoOfDays = maximumNoOfDays;
    }

    /**
     * 
     * @return
     *     The minimumNoOfDays
     */
    public Integer getMinimumNoOfDays() {
        return minimumNoOfDays;
    }

    /**
     * 
     * @param minimumNoOfDays
     *     The MinimumNoOfDays
     */
    public void setMinimumNoOfDays(Integer minimumNoOfDays) {
        this.minimumNoOfDays = minimumNoOfDays;
    }

    /**
     * 
     * @return
     *     The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 
     * @param shortName
     *     The ShortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 
     * @return
     *     The alternateStaffStatusDetails
     */
    public List<AlternateStaffStatusDetail> getAlternateStaffStatusDetails() {
        return alternateStaffStatusDetails;
    }

    /**
     * 
     * @param alternateStaffStatusDetails
     *     The AlternateStaffStatusDetails
     */
    public void setAlternateStaffStatusDetails(List<AlternateStaffStatusDetail> alternateStaffStatusDetails) {
        this.alternateStaffStatusDetails = alternateStaffStatusDetails;
    }

    /**
     * 
     * @return
     *     The applicationID
     */
    public Integer getApplicationID() {
        return applicationID;
    }

    /**
     * 
     * @param applicationID
     *     The ApplicationID
     */
    public void setApplicationID(Integer applicationID) {
        this.applicationID = applicationID;
    }

    /**
     * 
     * @return
     *     The appliedByID
     */
    public Integer getAppliedByID() {
        return appliedByID;
    }

    /**
     * 
     * @param appliedByID
     *     The AppliedByID
     */
    public void setAppliedByID(Integer appliedByID) {
        this.appliedByID = appliedByID;
    }

    /**
     * 
     * @return
     *     The appliedByName
     */
    public Object getAppliedByName() {
        return appliedByName;
    }

    /**
     * 
     * @param appliedByName
     *     The AppliedByName
     */
    public void setAppliedByName(Object appliedByName) {
        this.appliedByName = appliedByName;
    }

    /**
     * 
     * @return
     *     The approvalStatus
     */
    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * 
     * @param approvalStatus
     *     The ApprovalStatus
     */
    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
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
     *     The leaveAppliedDate
     */
    public String getLeaveAppliedDate() {
        return leaveAppliedDate;
    }

    /**
     * 
     * @param leaveAppliedDate
     *     The LeaveAppliedDate
     */
    public void setLeaveAppliedDate(String leaveAppliedDate) {
        this.leaveAppliedDate = leaveAppliedDate;
    }

    /**
     * 
     * @return
     *     The leaveDateFrom
     */
    public String getLeaveDateFrom() {
        return leaveDateFrom;
    }

    /**
     * 
     * @param leaveDateFrom
     *     The LeaveDateFrom
     */
    public void setLeaveDateFrom(String leaveDateFrom) {
        this.leaveDateFrom = leaveDateFrom;
    }

    /**
     * 
     * @return
     *     The leaveDateTo
     */
    public String getLeaveDateTo() {
        return leaveDateTo;
    }

    /**
     * 
     * @param leaveDateTo
     *     The LeaveDateTo
     */
    public void setLeaveDateTo(String leaveDateTo) {
        this.leaveDateTo = leaveDateTo;
    }

    /**
     * 
     * @return
     *     The leaveRequestSentTo
     */
    public String getLeaveRequestSentTo() {
        return leaveRequestSentTo;
    }

    /**
     * 
     * @param leaveRequestSentTo
     *     The LeaveRequestSentTo
     */
    public void setLeaveRequestSentTo(String leaveRequestSentTo) {
        this.leaveRequestSentTo = leaveRequestSentTo;
    }

    /**
     * 
     * @return
     *     The leaveStatusID
     */
    public Integer getLeaveStatusID() {
        return leaveStatusID;
    }

    /**
     * 
     * @param leaveStatusID
     *     The LeaveStatusID
     */
    public void setLeaveStatusID(Integer leaveStatusID) {
        this.leaveStatusID = leaveStatusID;
    }

    /**
     * 
     * @return
     *     The reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * 
     * @param reason
     *     The Reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

}
