
package app.managementapp.college.com.collegemanagement.api.StaffLoginAbsenteesDetails;


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
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("GUID")
    @Expose
    private String gUID;
    @SerializedName("InTime")
    @Expose
    private String inTime;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("OutTime")
    @Expose
    private String outTime;

    protected DataList(Parcel in) {
        code = in.readString();
        gUID = in.readString();
        inTime = in.readString();
        name = in.readString();
        outTime = in.readString();
    }

    /**
     * 
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The Code
     */
    public void setCode(String code) {
        this.code = code;
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
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The outTime
     */
    public String getOutTime() {
        return outTime;
    }

    /**
     * 
     * @param outTime
     *     The OutTime
     */
    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(gUID);
        dest.writeString(inTime);
        dest.writeString(name);
        dest.writeString(outTime);
    }
}
