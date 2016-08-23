
package app.managementapp.college.com.collegemanagement.api.StudentPersonalDetails;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class College {

    @SerializedName("AddressList")
    @Expose
    private List<Object> addressList = new ArrayList<Object>();
    @SerializedName("CollegeAddress")
    @Expose
    private CollegeAddress collegeAddress;
    @SerializedName("CollegeCode")
    @Expose
    private Object collegeCode;
    @SerializedName("CollegeID")
    @Expose
    private Integer collegeID;
    @SerializedName("CollegeName")
    @Expose
    private String collegeName;
    @SerializedName("CollegeShortName")
    @Expose
    private Object collegeShortName;
    @SerializedName("EMail")
    @Expose
    private Object eMail;
    @SerializedName("TelephoneNos")
    @Expose
    private Object telephoneNos;
    @SerializedName("TrustEMail")
    @Expose
    private Object trustEMail;
    @SerializedName("TrustName")
    @Expose
    private Object trustName;
    @SerializedName("TrustTelephoneNos")
    @Expose
    private Object trustTelephoneNos;
    @SerializedName("TrustWebsite")
    @Expose
    private Object trustWebsite;
    @SerializedName("Website")
    @Expose
    private Object website;

    /**
     * 
     * @return
     *     The addressList
     */
    public List<Object> getAddressList() {
        return addressList;
    }

    /**
     * 
     * @param addressList
     *     The AddressList
     */
    public void setAddressList(List<Object> addressList) {
        this.addressList = addressList;
    }

    /**
     * 
     * @return
     *     The collegeAddress
     */
    public CollegeAddress getCollegeAddress() {
        return collegeAddress;
    }

    /**
     * 
     * @param collegeAddress
     *     The CollegeAddress
     */
    public void setCollegeAddress(CollegeAddress collegeAddress) {
        this.collegeAddress = collegeAddress;
    }

    /**
     * 
     * @return
     *     The collegeCode
     */
    public Object getCollegeCode() {
        return collegeCode;
    }

    /**
     * 
     * @param collegeCode
     *     The CollegeCode
     */
    public void setCollegeCode(Object collegeCode) {
        this.collegeCode = collegeCode;
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
     *     The collegeShortName
     */
    public Object getCollegeShortName() {
        return collegeShortName;
    }

    /**
     * 
     * @param collegeShortName
     *     The CollegeShortName
     */
    public void setCollegeShortName(Object collegeShortName) {
        this.collegeShortName = collegeShortName;
    }

    /**
     * 
     * @return
     *     The eMail
     */
    public Object getEMail() {
        return eMail;
    }

    /**
     * 
     * @param eMail
     *     The EMail
     */
    public void setEMail(Object eMail) {
        this.eMail = eMail;
    }

    /**
     * 
     * @return
     *     The telephoneNos
     */
    public Object getTelephoneNos() {
        return telephoneNos;
    }

    /**
     * 
     * @param telephoneNos
     *     The TelephoneNos
     */
    public void setTelephoneNos(Object telephoneNos) {
        this.telephoneNos = telephoneNos;
    }

    /**
     * 
     * @return
     *     The trustEMail
     */
    public Object getTrustEMail() {
        return trustEMail;
    }

    /**
     * 
     * @param trustEMail
     *     The TrustEMail
     */
    public void setTrustEMail(Object trustEMail) {
        this.trustEMail = trustEMail;
    }

    /**
     * 
     * @return
     *     The trustName
     */
    public Object getTrustName() {
        return trustName;
    }

    /**
     * 
     * @param trustName
     *     The TrustName
     */
    public void setTrustName(Object trustName) {
        this.trustName = trustName;
    }

    /**
     * 
     * @return
     *     The trustTelephoneNos
     */
    public Object getTrustTelephoneNos() {
        return trustTelephoneNos;
    }

    /**
     * 
     * @param trustTelephoneNos
     *     The TrustTelephoneNos
     */
    public void setTrustTelephoneNos(Object trustTelephoneNos) {
        this.trustTelephoneNos = trustTelephoneNos;
    }

    /**
     * 
     * @return
     *     The trustWebsite
     */
    public Object getTrustWebsite() {
        return trustWebsite;
    }

    /**
     * 
     * @param trustWebsite
     *     The TrustWebsite
     */
    public void setTrustWebsite(Object trustWebsite) {
        this.trustWebsite = trustWebsite;
    }

    /**
     * 
     * @return
     *     The website
     */
    public Object getWebsite() {
        return website;
    }

    /**
     * 
     * @param website
     *     The Website
     */
    public void setWebsite(Object website) {
        this.website = website;
    }

}
