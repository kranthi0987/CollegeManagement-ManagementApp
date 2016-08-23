
package app.managementapp.college.com.collegemanagement.api.StudentSearch.SemesterList;

import java.util.HashMap;
import java.util.Map;

public class DataList {

    private String drpID;
    private String drpName;
    private String filterID;
    private String nomenClature;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
