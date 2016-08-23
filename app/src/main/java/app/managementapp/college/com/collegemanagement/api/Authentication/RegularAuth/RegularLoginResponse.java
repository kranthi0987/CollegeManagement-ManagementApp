
package app.managementapp.college.app.managementapp.college.com.collegemanagement.api.Authentication.RegularAuth;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class RegularLoginResponse implements Parcelable {

    private Integer authenticationResult;
    private Integer serviceResult;
    private String token;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected RegularLoginResponse(Parcel in) {
        token = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegularLoginResponse> CREATOR = new Creator<RegularLoginResponse>() {
        @Override
        public RegularLoginResponse createFromParcel(Parcel in) {
            return new RegularLoginResponse(in);
        }

        @Override
        public RegularLoginResponse[] newArray(int size) {
            return new RegularLoginResponse[size];
        }
    };

    /**
     * 
     * @return
     *     The authenticationResult
     */
    public Integer getAuthenticationResult() {
        return authenticationResult;
    }

    /**
     * 
     * @param authenticationResult
     *     The AuthenticationResult
     */
    public void setAuthenticationResult(Integer authenticationResult) {
        this.authenticationResult = authenticationResult;
    }

    /**
     * 
     * @return
     *     The serviceResult
     */
    public Integer getServiceResult() {
        return serviceResult;
    }

    /**
     * 
     * @param serviceResult
     *     The ServiceResult
     */
    public void setServiceResult(Integer serviceResult) {
        this.serviceResult = serviceResult;
    }

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegularLoginResponse that = (RegularLoginResponse) o;

        if (!authenticationResult.equals(that.authenticationResult)) return false;
        if (!serviceResult.equals(that.serviceResult)) return false;
        if (!token.equals(that.token)) return false;
        return additionalProperties.equals(that.additionalProperties);

    }

    @Override
    public int hashCode() {
        int result = authenticationResult.hashCode();
        result = 31 * result + serviceResult.hashCode();
        result = 31 * result + token.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    /**
     * 
     * @param token

     *     The Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



}
