
package app.managementapp.college.com.collegemanagement.api.Profiles.University;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataList {

    private List<CollegeList> collegeList = new ArrayList<CollegeList>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

}
