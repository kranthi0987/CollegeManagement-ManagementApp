
package app.managementapp.college.com.collegemanagement.api.StudentActivities;


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

    protected DataList(Parcel in) {
        action = in.readString();
        activity = in.readString();
        description = in.readString();
        memoDate = in.readString();
        reportedBy = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(action);
        dest.writeString(activity);
        dest.writeString(description);
        dest.writeString(memoDate);
        dest.writeString(reportedBy);
    }

    @Override
    public int describeContents() {
        return 0;
    }

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
