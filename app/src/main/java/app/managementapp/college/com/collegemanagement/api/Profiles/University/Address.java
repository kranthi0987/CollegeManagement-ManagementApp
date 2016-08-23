
package app.managementapp.college.com.collegemanagement.api.Profiles.University;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Address implements Parcelable {

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
    private String address;
    private String addressType;
    private String city;
    private String country;
    private String mobile;
    private String phone;
    private String state;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected Address(Parcel in) {
        address = in.readString();
        addressType = in.readString();
        city = in.readString();
        country = in.readString();
        mobile = in.readString();
        phone = in.readString();
        state = in.readString();
    }

    /**
     * 
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The addressType
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * 
     * @param addressType
     *     The AddressType
     */
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The City
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The Country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile
     *     The Mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 
     * @return
     *     The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone
     *     The Phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The State
     */
    public void setState(String state) {
        this.state = state;
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
        dest.writeString(address);
        dest.writeString(addressType);
        dest.writeString(city);
        dest.writeString(country);
        dest.writeString(mobile);
        dest.writeString(phone);
        dest.writeString(state);
    }
}
