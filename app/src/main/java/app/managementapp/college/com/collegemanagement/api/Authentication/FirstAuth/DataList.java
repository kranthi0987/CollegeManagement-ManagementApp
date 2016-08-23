
package app.managementapp.college.app.managementapp.college.com.collegemanagement.api.Authentication.FirstAuth;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class DataList implements Parcelable{

    private Address address;
    private String base64Image;
    private String universityName;
    private Object universityShortName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected DataList(Parcel in) {
        base64Image = in.readString();
        universityName = in.readString();
    }

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
    public Object getUniversityShortName() {
        return universityShortName;
    }

    /**
     * 
     * @param universityShortName
     *     The UniversityShortName
     */
    public void setUniversityShortName(Object universityShortName) {
        this.universityShortName = universityShortName;
    }


    @Override
    public String toString() {
        return "DataList{" +
                "address=" + address +
                ", base64Image='" + base64Image + '\'' +
                ", universityName='" + universityName + '\'' +
                ", universityShortName=" + universityShortName +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataList dataList = (DataList) o;

        if (address != null ? !address.equals(dataList.address) : dataList.address != null)
            return false;
        if (base64Image != null ? !base64Image.equals(dataList.base64Image) : dataList.base64Image != null)
            return false;
        if (universityName != null ? !universityName.equals(dataList.universityName) : dataList.universityName != null)
            return false;
        if (universityShortName != null ? !universityShortName.equals(dataList.universityShortName) : dataList.universityShortName != null)
            return false;
        return additionalProperties != null ? additionalProperties.equals(dataList.additionalProperties) : dataList.additionalProperties == null;

    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (base64Image != null ? base64Image.hashCode() : 0);
        result = 31 * result + (universityName != null ? universityName.hashCode() : 0);
        result = 31 * result + (universityShortName != null ? universityShortName.hashCode() : 0);
        result = 31 * result + (additionalProperties != null ? additionalProperties.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(base64Image);
        dest.writeString(universityName);
    }
}
