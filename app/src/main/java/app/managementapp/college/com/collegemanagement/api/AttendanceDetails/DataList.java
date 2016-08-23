
package app.managementapp.college.com.collegemanagement.api.AttendanceDetails;


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
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("<DaysAttended>k__BackingField")
    @Expose
    private String daysAttendedKBackingField;
    @SerializedName("<Percentage>k__BackingField")
    @Expose
    private Integer percentageKBackingField;
    @SerializedName("Held")
    @Expose
    private Integer held;
    @SerializedName("Sem")
    @Expose
    private String sem;

    protected DataList(Parcel in) {
        code = in.readString();
        name = in.readString();
        daysAttendedKBackingField = in.readString();
        sem = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(daysAttendedKBackingField);
        dest.writeString(sem);
    }

    @Override
    public int describeContents() {
        return 0;
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
     *     The iD
     */
    public Integer getID() {
        return iD;
    }

    /**
     * 
     * @param iD
     *     The ID
     */
    public void setID(Integer iD) {
        this.iD = iD;
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
     *     The daysAttendedKBackingField
     */
    public String getDaysAttendedKBackingField() {
        return daysAttendedKBackingField;
    }

    /**
     * 
     * @param daysAttendedKBackingField
     *     The <DaysAttended>k__BackingField
     */
    public void setDaysAttendedKBackingField(String daysAttendedKBackingField) {
        this.daysAttendedKBackingField = daysAttendedKBackingField;
    }

    /**
     * 
     * @return
     *     The percentageKBackingField
     */
    public Integer getPercentageKBackingField() {
        return percentageKBackingField;
    }

    /**
     * 
     * @param percentageKBackingField
     *     The <Percentage>k__BackingField
     */
    public void setPercentageKBackingField(Integer percentageKBackingField) {
        this.percentageKBackingField = percentageKBackingField;
    }

    /**
     * 
     * @return
     *     The held
     */
    public Integer getHeld() {
        return held;
    }

    /**
     * 
     * @param held
     *     The Held
     */
    public void setHeld(Integer held) {
        this.held = held;
    }

    /**
     * 
     * @return
     *     The sem
     */
    public String getSem() {
        return sem;
    }

    /**
     * 
     * @param sem
     *     The Sem
     */
    public void setSem(String sem) {
        this.sem = sem;
    }

}
