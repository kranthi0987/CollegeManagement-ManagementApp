
package app.managementapp.college.com.collegemanagement.api.Semester;


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
    @SerializedName("DrpID")
    @Expose
    private String drpID;
    @SerializedName("DrpName")
    @Expose
    private String drpName;
    @SerializedName("FilterID")
    @Expose
    private String filterID;
    @SerializedName("NomenClature")
    @Expose
    private String nomenClature;

    protected DataList(Parcel in) {
        drpID = in.readString();
        drpName = in.readString();
        filterID = in.readString();
        nomenClature = in.readString();
    }

    /**
     * 
     * @return
     *     The drpID
     */
    public String getDrpID() {
        return drpID;
    }

    /**
     * 
     * @param drpID
     *     The DrpID
     */
    public void setDrpID(String drpID) {
        this.drpID = drpID;
    }

    /**
     * 
     * @return
     *     The drpName
     */
    public String getDrpName() {
        return drpName;
    }

    /**
     * 
     * @param drpName
     *     The DrpName
     */
    public void setDrpName(String drpName) {
        this.drpName = drpName;
    }

    /**
     * 
     * @return
     *     The filterID
     */
    public String getFilterID() {
        return filterID;
    }

    /**
     * 
     * @param filterID
     *     The FilterID
     */
    public void setFilterID(String filterID) {
        this.filterID = filterID;
    }

    /**
     * 
     * @return
     *     The nomenClature
     */
    public String getNomenClature() {
        return nomenClature;
    }

    /**
     * 
     * @param nomenClature
     *     The NomenClature
     */
    public void setNomenClature(String nomenClature) {
        this.nomenClature = nomenClature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(drpID);
        dest.writeString(drpName);
        dest.writeString(filterID);
        dest.writeString(nomenClature);
    }
}
