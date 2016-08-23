
package app.managementapp.college.com.collegemanagement.api.Profiles.College;

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
    private Address address;
    private String base64Image;
    private String universityName;
    private String universityShortName;
    private Integer collegeID;
    private String collegeName;
    private List<DepartmentList> departmentList = new ArrayList<DepartmentList>();
    private String email;
    private String fax;
    private String webSite;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected DataList(Parcel in) {
        address = in.readParcelable(Address.class.getClassLoader());
        base64Image = in.readString();
        universityName = in.readString();
        universityShortName = in.readString();
        collegeName = in.readString();
        email = in.readString();
        fax = in.readString();
        webSite = in.readString();
    }

    /**
     * 
     * @return
     *     The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The Address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The base64Image
     */
    public String getBase64Image() {
        return base64Image;
    }

    /**
     * 
     * @param base64Image
     *     The Base64Image
     */
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    /**
     * 
     * @return
     *     The universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * 
     * @param universityName
     *     The UniversityName
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * 
     * @return
     *     The universityShortName
     */
    public String getUniversityShortName() {
        return universityShortName;
    }

    /**
     * 
     * @param universityShortName
     *     The UniversityShortName
     */
    public void setUniversityShortName(String universityShortName) {
        this.universityShortName = universityShortName;
    }

    /**
     * 
     * @return
     *     The collegeID
     */
    public Integer getCollegeID() {
        return collegeID;
    }

    /**
     * 
     * @param collegeID
     *     The CollegeID
     */
    public void setCollegeID(Integer collegeID) {
        this.collegeID = collegeID;
    }

    /**
     * 
     * @return
     *     The collegeName
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * 
     * @param collegeName
     *     The CollegeName
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * 
     * @return
     *     The departmentList
     */
    public List<DepartmentList> getDepartmentList() {
        return departmentList;
    }

    /**
     * 
     * @param departmentList
     *     The DepartmentList
     */
    public void setDepartmentList(List<DepartmentList> departmentList) {
        this.departmentList = departmentList;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * 
     * @param fax
     *     The Fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 
     * @return
     *     The webSite
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * 
     * @param webSite
     *     The WebSite
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
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
        dest.writeParcelable(address, flags);
        dest.writeString(base64Image);
        dest.writeString(universityName);
        dest.writeString(universityShortName);
        dest.writeString(collegeName);
        dest.writeString(email);
        dest.writeString(fax);
        dest.writeString(webSite);
    }
}
