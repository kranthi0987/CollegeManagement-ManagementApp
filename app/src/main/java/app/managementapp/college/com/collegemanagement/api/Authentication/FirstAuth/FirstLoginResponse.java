
package app.managementapp.college.com.collegemanagement.api.Authentication.FirstAuth;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstLoginResponse implements Parcelable{

    public static final Creator<FirstLoginResponse> CREATOR = new Creator<FirstLoginResponse>() {
        @Override
        public FirstLoginResponse createFromParcel(Parcel in) {
            return new FirstLoginResponse(in);
        }

        @Override
        public FirstLoginResponse[] newArray(int size) {
            return new FirstLoginResponse[size];
        }
    };
    private List<DataList> dataList = new ArrayList<DataList>();
    private Object errorMessage;
    private String extendedToken;
    private Integer serviceResult;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected FirstLoginResponse(Parcel in) {
        extendedToken = in.readString();
    }

    @Override
    public String toString() {
        return "FirstLoginResponse{" +
                "dataList=" + dataList +
                ", errorMessage=" + errorMessage +
                ", extendedToken='" + extendedToken + '\'' +
                ", serviceResult=" + serviceResult +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirstLoginResponse that = (FirstLoginResponse) o;

        if (dataList != null ? !dataList.equals(that.dataList) : that.dataList != null)
            return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null)
            return false;
        if (extendedToken != null ? !extendedToken.equals(that.extendedToken) : that.extendedToken != null)
            return false;
        if (serviceResult != null ? !serviceResult.equals(that.serviceResult) : that.serviceResult != null)
            return false;
        return additionalProperties != null ? additionalProperties.equals(that.additionalProperties) : that.additionalProperties == null;

    }

    @Override
    public int hashCode() {
        int result = dataList != null ? dataList.hashCode() : 0;
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (extendedToken != null ? extendedToken.hashCode() : 0);
        result = 31 * result + (serviceResult != null ? serviceResult.hashCode() : 0);
        result = 31 * result + (additionalProperties != null ? additionalProperties.hashCode() : 0);
        return result;
    }

    /**
     * 
     * @return
     *     The dataList
     */
    public List<DataList> getDataList() {
        return dataList;
    }

    /**
     * 
     * @param dataList
     *     The DataList
     */
    public void setDataList(List<DataList> dataList) {
        this.dataList = dataList;
    }

    /**
     * 
     * @return
     *     The errorMessage
     */
    public Object getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @param errorMessage
     *     The ErrorMessage
     */
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 
     * @return
     *     The extendedToken
     */
    public String getExtendedToken() {
        return extendedToken;
    }

    /**
     * 
     * @param extendedToken
     *     The ExtendedToken
     */
    public void setExtendedToken(String extendedToken) {
        this.extendedToken = extendedToken;
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
        dest.writeString(extendedToken);
    }
}
