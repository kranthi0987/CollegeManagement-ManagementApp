
package app.managementapp.college.com.collegemanagement.api.UniversityProfile;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


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
    @SerializedName("CollegeList")
    @Expose
    private List<CollegeList> collegeList = new ArrayList<CollegeList>();
    @SerializedName("UniversityID")
    @Expose
    private Integer universityID;
    @SerializedName("UniversityName")
    @Expose
    private Object universityName;

    protected DataList(Parcel in) {
    }

    /**
     * 
     * @return
     *     The collegeList
     */
    public List<CollegeList> getCollegeList() {
        return collegeList;
    }

    /**
     * 
     * @param collegeList
     *     The CollegeList
     */
    public void setCollegeList(List<CollegeList> collegeList) {
        this.collegeList = collegeList;
    }

    /**
     * 
     * @return
     *     The universityID
     */
    public Integer getUniversityID() {
        return universityID;
    }

    /**
     * 
     * @param universityID
     *     The UniversityID
     */
    public void setUniversityID(Integer universityID) {
        this.universityID = universityID;
    }

    /**
     * 
     * @return
     *     The universityName
     */
    public Object getUniversityName() {
        return universityName;
    }

    /**
     * 
     * @param universityName
     *     The UniversityName
     */
    public void setUniversityName(Object universityName) {
        this.universityName = universityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
