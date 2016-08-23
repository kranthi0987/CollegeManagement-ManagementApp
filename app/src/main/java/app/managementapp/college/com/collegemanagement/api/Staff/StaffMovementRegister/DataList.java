
package app.managementapp.college.com.collegemanagement.api.Staff.StaffMovementRegister;


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
    @SerializedName("Time")
    @Expose
    private String time;

    protected DataList(Parcel in) {
        date = in.readString();
        time = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
    }
}
