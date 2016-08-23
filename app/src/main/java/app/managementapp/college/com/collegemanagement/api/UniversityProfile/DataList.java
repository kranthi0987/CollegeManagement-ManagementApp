
package app.managementapp.college.com.collegemanagement.api.UniversityProfile;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("CollegeList")
    @Expose
    private List<CollegeList> collegeList = new ArrayList<CollegeList>();
    @SerializedName("UniversityID")
    @Expose
    private Integer universityID;
    @SerializedName("UniversityName")
    @Expose
    private Object universityName;

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

}
