
package app.managementapp.college.com.collegemanagement.api.Staff.StaffSeminars;


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

    protected DataList(Parcel in) {
        certificateName = in.readString();
        certifiedDate = in.readString();
        certifiedFrom = in.readString();
        description = in.readString();
        endDateTime = in.readString();
        name = in.readString();
        startDateTime = in.readString();
        type = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(certificateName);
        dest.writeString(certifiedDate);
        dest.writeString(certifiedFrom);
        dest.writeString(description);
        dest.writeString(endDateTime);
        dest.writeString(name);
        dest.writeString(startDateTime);
        dest.writeString(type);
    }
}
