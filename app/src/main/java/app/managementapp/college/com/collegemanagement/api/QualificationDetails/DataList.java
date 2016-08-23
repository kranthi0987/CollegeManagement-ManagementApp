
package app.managementapp.college.com.collegemanagement.api.QualificationDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("Board")
    @Expose
    private String board;
    @SerializedName("College")
    @Expose
    private String college;
    @SerializedName("PCMPercent")
    @Expose
    private Integer pCMPercent;
    @SerializedName("QualifyingCourse")
    @Expose
    private String qualifyingCourse;
    @SerializedName("SSLCorSSCPercent")
    @Expose
    private Integer sSLCorSSCPercent;
    @SerializedName("TotalPercent")
    @Expose
    private Integer totalPercent;
    @SerializedName("USN")
    @Expose
    private String uSN;

    /**
     * 
     * @return
     *     The board
     */
    public String getBoard() {
        return board;
    }

    /**
     * 
     * @param board
     *     The Board
     */
    public void setBoard(String board) {
        this.board = board;
    }

    /**
     * 
     * @return
     *     The college
     */
    public String getCollege() {
        return college;
    }

    /**
     * 
     * @param college
     *     The College
     */
    public void setCollege(String college) {
        this.college = college;
    }

    /**
     * 
     * @return
     *     The pCMPercent
     */
    public Integer getPCMPercent() {
        return pCMPercent;
    }

    /**
     * 
     * @param pCMPercent
     *     The PCMPercent
     */
    public void setPCMPercent(Integer pCMPercent) {
        this.pCMPercent = pCMPercent;
    }

    /**
     * 
     * @return
     *     The qualifyingCourse
     */
    public String getQualifyingCourse() {
        return qualifyingCourse;
    }

    /**
     * 
     * @param qualifyingCourse
     *     The QualifyingCourse
     */
    public void setQualifyingCourse(String qualifyingCourse) {
        this.qualifyingCourse = qualifyingCourse;
    }

    /**
     * 
     * @return
     *     The sSLCorSSCPercent
     */
    public Integer getSSLCorSSCPercent() {
        return sSLCorSSCPercent;
    }

    /**
     * 
     * @param sSLCorSSCPercent
     *     The SSLCorSSCPercent
     */
    public void setSSLCorSSCPercent(Integer sSLCorSSCPercent) {
        this.sSLCorSSCPercent = sSLCorSSCPercent;
    }

    /**
     * 
     * @return
     *     The totalPercent
     */
    public Integer getTotalPercent() {
        return totalPercent;
    }

    /**
     * 
     * @param totalPercent
     *     The TotalPercent
     */
    public void setTotalPercent(Integer totalPercent) {
        this.totalPercent = totalPercent;
    }

    /**
     * 
     * @return
     *     The uSN
     */
    public String getUSN() {
        return uSN;
    }

    /**
     * 
     * @param uSN
     *     The USN
     */
    public void setUSN(String uSN) {
        this.uSN = uSN;
    }

}
