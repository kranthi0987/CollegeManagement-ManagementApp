
package app.managementapp.college.com.collegemanagement.api.AcademicCalender;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AcademicCalenderResponse implements Parcelable {

    public static final Creator<AcademicCalenderResponse> CREATOR = new Creator<AcademicCalenderResponse>() {
        @Override
        public AcademicCalenderResponse createFromParcel(Parcel in) {
            return new AcademicCalenderResponse(in);
        }

        @Override
        public AcademicCalenderResponse[] newArray(int size) {
            return new AcademicCalenderResponse[size];
        }
    };
    @SerializedName("DataList")
    @Expose
    private List<app.managementapp.college.com.collegemanagement.api.AcademicCalender.DataList> dataList = new ArrayList<app.managementapp.college.com.collegemanagement.api.AcademicCalender.DataList>();
    @SerializedName("ErrorMessage")
    @Expose
    private Object errorMessage;
    @SerializedName("ExtendedToken")
    @Expose
    private String extendedToken;
    @SerializedName("ServiceResult")
    @Expose
    private Integer serviceResult;

    protected AcademicCalenderResponse(Parcel in) {
        extendedToken = in.readString();
    }

    /**
     * 
     * @return
     *     The dataList
     */
    public List<app.managementapp.college.com.collegemanagement.api.AcademicCalender.DataList> getDataList() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(extendedToken);
    }
}
