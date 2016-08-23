
package app.managementapp.college.com.collegemanagement.api.StaffLoginAbsenteesDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("GUID")
    @Expose
    private String gUID;
    @SerializedName("InTime")
    @Expose
    private String inTime;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("OutTime")
    @Expose
    private String outTime;

    /**
     * 
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The Code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The gUID
     */
    public String getGUID() {
        return gUID;
    }

    /**
     * 
     * @param gUID
     *     The GUID
     */
    public void setGUID(String gUID) {
        this.gUID = gUID;
    }

    /**
     * 
     * @return
     *     The inTime
     */
    public String getInTime() {
        return inTime;
    }

    /**
     * 
     * @param inTime
     *     The InTime
     */
    public void setInTime(String inTime) {
        this.inTime = inTime;
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
     *     The outTime
     */
    public String getOutTime() {
        return outTime;
    }

    /**
     * 
     * @param outTime
     *     The OutTime
     */
    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

}
