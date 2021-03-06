
package app.managementapp.college.com.collegemanagement.api.Staff.StaffAttendance;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList implements Parcelable {

    public static final Creator<DataList> CREATOR = new Creator<DataList>() {
        @Override
        public DataList createFromParcel(Parcel in) {
            return new DataList(in);
        }

        @Override
        public DataList[] newArray(int size) {
            return new DataList[size];
        }
    };
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("InTime")
    @Expose
    private String inTime;
    @SerializedName("IsLateSanctioned")
    @Expose
    private Boolean isLateSanctioned;
    @SerializedName("LateRemark")
    @Expose
    private String lateRemark;
    @SerializedName("LateSanctionedBy")
    @Expose
    private Object lateSanctionedBy;
    @SerializedName("OutTime")
    @Expose
    private Object outTime;

    protected DataList(Parcel in) {
        date = in.readString();
        inTime = in.readString();
        lateRemark = in.readString();
    }

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
     *     The inTime
     */
    public String getInTime() {
        return inTime;
    }

    /**
     * 
     * @param inTime
     *     The InTime
     */
    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    /**
     * 
     * @return
     *     The isLateSanctioned
     */
    public Boolean getIsLateSanctioned() {
        return isLateSanctioned;
    }

    /**
     * 
     * @param isLateSanctioned
     *     The IsLateSanctioned
     */
    public void setIsLateSanctioned(Boolean isLateSanctioned) {
        this.isLateSanctioned = isLateSanctioned;
    }

    /**
     * 
     * @return
     *     The lateRemark
     */
    public String getLateRemark() {
        return lateRemark;
    }

    /**
     * 
     * @param lateRemark
     *     The LateRemark
     */
    public void setLateRemark(String lateRemark) {
        this.lateRemark = lateRemark;
    }

    /**
     * 
     * @return
     *     The lateSanctionedBy
     */
    public Object getLateSanctionedBy() {
        return lateSanctionedBy;
    }

    /**
     * 
     * @param lateSanctionedBy
     *     The LateSanctionedBy
     */
    public void setLateSanctionedBy(Object lateSanctionedBy) {
        this.lateSanctionedBy = lateSanctionedBy;
    }

    /**
     * 
     * @return
     *     The outTime
     */
    public Object getOutTime() {
        return outTime;
    }

    /**
     * 
     * @param outTime
     *     The OutTime
     */
    public void setOutTime(Object outTime) {
        this.outTime = outTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(inTime);
        dest.writeString(lateRemark);
    }
}
