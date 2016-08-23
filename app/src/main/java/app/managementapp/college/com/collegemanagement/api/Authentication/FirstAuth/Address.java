
package app.managementapp.college.com.collegemanagement.api.Authentication.FirstAuth;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Address implements Parcelable{

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
    private Object addressType;
    private String city;
    private String country;
    private Object mobile;
    private Object phone;
    private String state;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected Address(Parcel in) {
        address = in.readString();
        city = in.readString();
        country = in.readString();
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
    public Object getAddressType() {
        return addressType;
    }

    /**
     * 
     * @param addressType
     *     The AddressType
     */
    public void setAddressType(Object addressType) {
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
    public Object getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile
     *     The Mobile
     */
    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    /**
     * 
     * @return
     *     The phone
     */
    public Object getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone
     *     The Phone
     */
    public void setPhone(Object phone) {
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

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", addressType=" + addressType +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", mobile=" + mobile +
                ", phone=" + phone +
                ", state='" + state + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (address != null ? !address.equals(address1.address) : address1.address != null)
            return false;
        if (addressType != null ? !addressType.equals(address1.addressType) : address1.addressType != null)
            return false;
        if (city != null ? !city.equals(address1.city) : address1.city != null) return false;
        if (country != null ? !country.equals(address1.country) : address1.country != null)
            return false;
        if (mobile != null ? !mobile.equals(address1.mobile) : address1.mobile != null)
            return false;
        if (phone != null ? !phone.equals(address1.phone) : address1.phone != null) return false;
        if (state != null ? !state.equals(address1.state) : address1.state != null) return false;
        return additionalProperties != null ? additionalProperties.equals(address1.additionalProperties) : address1.additionalProperties == null;

    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (addressType != null ? addressType.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (additionalProperties != null ? additionalProperties.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(country);
        dest.writeString(state);
    }
}
