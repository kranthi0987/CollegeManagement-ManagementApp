
package app.managementapp.college.com.collegemanagement.api.QualificationDetails;


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

    protected DataList(Parcel in) {
        board = in.readString();
        college = in.readString();
        qualifyingCourse = in.readString();
        uSN = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(board);
        dest.writeString(college);
        dest.writeString(qualifyingCourse);
        dest.writeString(uSN);
    }
}
