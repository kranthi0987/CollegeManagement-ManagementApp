
package app.managementapp.college.com.collegemanagement.api.Profiles.University;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<CollegeList> collegeList = new ArrayList<CollegeList>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    }
}
