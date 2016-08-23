
package app.managementapp.college.com.collegemanagement.api.Profiles.College;

import java.util.HashMap;
import java.util.Map;

public class DepartmentList {

    private Integer departmentHODID;
    private Integer departmentID;
    private String departmentName;
    private String hODName;
    private String hODPhone;
    private String mGUID;
    private String qualification;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The departmentHODID
     */
    public Integer getDepartmentHODID() {
        return departmentHODID;
    }

    /**
     * 
     * @param departmentHODID
     *     The DepartmentHODID
     */
    public void setDepartmentHODID(Integer departmentHODID) {
        this.departmentHODID = departmentHODID;
    }

    /**
     * 
     * @return
     *     The departmentID
     */
    public Integer getDepartmentID() {
        return departmentID;
    }

    /**
     * 
     * @param departmentID
     *     The DepartmentID
     */
    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * 
     * @return
     *     The departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 
     * @param departmentName
     *     The DepartmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 
     * @return
     *     The hODName
     */
    public String getHODName() {
        return hODName;
    }

    /**
     * 
     * @param hODName
     *     The HODName
     */
    public void setHODName(String hODName) {
        this.hODName = hODName;
    }

    /**
     * 
     * @return
     *     The hODPhone
     */
    public String getHODPhone() {
        return hODPhone;
    }

    /**
     * 
     * @param hODPhone
     *     The HODPhone
     */
    public void setHODPhone(String hODPhone) {
        this.hODPhone = hODPhone;
    }

    /**
     * 
     * @return
     *     The mGUID
     */
    public String getMGUID() {
        return mGUID;
    }

    /**
     * 
     * @param mGUID
     *     The MGUID
     */
    public void setMGUID(String mGUID) {
        this.mGUID = mGUID;
    }

    /**
     * 
     * @return
     *     The qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * 
     * @param qualification
     *     The Qualification
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
