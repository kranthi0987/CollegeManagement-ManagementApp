
package app.managementapp.college.com.collegemanagement.api.Staff.StaffSeminars;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("CertificateName")
    @Expose
    private String certificateName;
    @SerializedName("CertifiedDate")
    @Expose
    private String certifiedDate;
    @SerializedName("CertifiedFrom")
    @Expose
    private String certifiedFrom;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("EndDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("StartDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("Type")
    @Expose
    private String type;

    /**
     * 
     * @return
     *     The certificateName
     */
    public String getCertificateName() {
        return certificateName;
    }

    /**
     * 
     * @param certificateName
     *     The CertificateName
     */
    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    /**
     * 
     * @return
     *     The certifiedDate
     */
    public String getCertifiedDate() {
        return certifiedDate;
    }

    /**
     * 
     * @param certifiedDate
     *     The CertifiedDate
     */
    public void setCertifiedDate(String certifiedDate) {
        this.certifiedDate = certifiedDate;
    }

    /**
     * 
     * @return
     *     The certifiedFrom
     */
    public String getCertifiedFrom() {
        return certifiedFrom;
    }

    /**
     * 
     * @param certifiedFrom
     *     The CertifiedFrom
     */
    public void setCertifiedFrom(String certifiedFrom) {
        this.certifiedFrom = certifiedFrom;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The endDateTime
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     * 
     * @param endDateTime
     *     The EndDateTime
     */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
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
     *     The startDateTime
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     * 
     * @param startDateTime
     *     The StartDateTime
     */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The Type
     */
    public void setType(String type) {
        this.type = type;
    }

}
