
package app.managementapp.college.com.collegemanagement.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class AlternateStaffStatusDetail implements Parcelable {

    public static final Creator<AlternateStaffStatusDetail> CREATOR = new Creator<AlternateStaffStatusDetail>() {
        @Override
        public AlternateStaffStatusDetail createFromParcel(Parcel in) {
            return new AlternateStaffStatusDetail(in);
        }

        @Override
        public AlternateStaffStatusDetail[] newArray(int size) {
            return new AlternateStaffStatusDetail[size];
        }
    };
    private Object alternateStaffName;
    private String comment;
    private String gUID;
    private Boolean isModified;
    private Integer scheduleID;
    private Integer status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected AlternateStaffStatusDetail(Parcel in) {
        comment = in.readString();
        gUID = in.readString();
    }

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(comment);
        dest.writeString(gUID);
    }
}
