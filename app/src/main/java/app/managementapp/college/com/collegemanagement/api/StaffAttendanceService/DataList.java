
package app.managementapp.college.com.collegemanagement.api.StaffAttendanceService;


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
    @SerializedName("NotificationDescription")
    @Expose
    private String notificationDescription;
    @SerializedName("NotificationTitle")
    @Expose
    private String notificationTitle;
    @SerializedName("RefID")
    @Expose
    private Integer refID;
    @SerializedName("SentByGUID")
    @Expose
    private Object sentByGUID;
    @SerializedName("SentByName")
    @Expose
    private Object sentByName;

    protected DataList(Parcel in) {
        notificationDescription = in.readString();
        notificationTitle = in.readString();
    }

    /**
     * 
     * @return
     *     The notificationDescription
     */
    public String getNotificationDescription() {
        return notificationDescription;
    }

    /**
     * 
     * @param notificationDescription
     *     The NotificationDescription
     */
    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    /**
     * 
     * @return
     *     The notificationTitle
     */
    public String getNotificationTitle() {
        return notificationTitle;
    }

    /**
     * 
     * @param notificationTitle
     *     The NotificationTitle
     */
    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    /**
     * 
     * @return
     *     The refID
     */
    public Integer getRefID() {
        return refID;
    }

    /**
     * 
     * @param refID
     *     The RefID
     */
    public void setRefID(Integer refID) {
        this.refID = refID;
    }

    /**
     * 
     * @return
     *     The sentByGUID
     */
    public Object getSentByGUID() {
        return sentByGUID;
    }

    /**
     * 
     * @param sentByGUID
     *     The SentByGUID
     */
    public void setSentByGUID(Object sentByGUID) {
        this.sentByGUID = sentByGUID;
    }

    /**
     * 
     * @return
     *     The sentByName
     */
    public Object getSentByName() {
        return sentByName;
    }

    /**
     * 
     * @param sentByName
     *     The SentByName
     */
    public void setSentByName(Object sentByName) {
        this.sentByName = sentByName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(notificationDescription);
        dest.writeString(notificationTitle);
    }
}
