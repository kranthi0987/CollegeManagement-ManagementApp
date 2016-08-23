
package app.managementapp.college.com.collegemanagement.api.Semester;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("DrpID")
    @Expose
    private String drpID;
    @SerializedName("DrpName")
    @Expose
    private String drpName;
    @SerializedName("FilterID")
    @Expose
    private String filterID;
    @SerializedName("NomenClature")
    @Expose
    private String nomenClature;

    /**
     * 
     * @return
     *     The drpID
     */
    public String getDrpID() {
        return drpID;
    }

    /**
     * 
     * @param drpID
     *     The DrpID
     */
    public void setDrpID(String drpID) {
        this.drpID = drpID;
    }

    /**
     * 
     * @return
     *     The drpName
     */
    public String getDrpName() {
        return drpName;
    }

    /**
     * 
     * @param drpName
     *     The DrpName
     */
    public void setDrpName(String drpName) {
        this.drpName = drpName;
    }

    /**
     * 
     * @return
     *     The filterID
     */
    public String getFilterID() {
        return filterID;
    }

    /**
     * 
     * @param filterID
     *     The FilterID
     */
    public void setFilterID(String filterID) {
        this.filterID = filterID;
    }

    /**
     * 
     * @return
     *     The nomenClature
     */
    public String getNomenClature() {
        return nomenClature;
    }

    /**
     * 
     * @param nomenClature
     *     The NomenClature
     */
    public void setNomenClature(String nomenClature) {
        this.nomenClature = nomenClature;
    }

}
