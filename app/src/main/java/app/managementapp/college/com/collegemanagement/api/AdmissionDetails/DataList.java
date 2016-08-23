
package app.managementapp.college.com.collegemanagement.api.AdmissionDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("AdmissionMonth")
    @Expose
    private Integer admissionMonth;
    @SerializedName("AdmissionYear")
    @Expose
    private Integer admissionYear;
    @SerializedName("ApplicationNo")
    @Expose
    private String applicationNo;
    @SerializedName("Branch")
    @Expose
    private String branch;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("ComedKNo")
    @Expose
    private String comedKNo;
    @SerializedName("ComedKRank")
    @Expose
    private Integer comedKRank;
    @SerializedName("Course")
    @Expose
    private String course;
    @SerializedName("EntranceExam")
    @Expose
    private String entranceExam;
    @SerializedName("Quota")
    @Expose
    private String quota;
    @SerializedName("Sem")
    @Expose
    private String sem;

    /**
     * 
     * @return
     *     The admissionMonth
     */
    public Integer getAdmissionMonth() {
        return admissionMonth;
    }

    /**
     * 
     * @param admissionMonth
     *     The AdmissionMonth
     */
    public void setAdmissionMonth(Integer admissionMonth) {
        this.admissionMonth = admissionMonth;
    }

    /**
     * 
     * @return
     *     The admissionYear
     */
    public Integer getAdmissionYear() {
        return admissionYear;
    }

    /**
     * 
     * @param admissionYear
     *     The AdmissionYear
     */
    public void setAdmissionYear(Integer admissionYear) {
        this.admissionYear = admissionYear;
    }

    /**
     * 
     * @return
     *     The applicationNo
     */
    public String getApplicationNo() {
        return applicationNo;
    }

    /**
     * 
     * @param applicationNo
     *     The ApplicationNo
     */
    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    /**
     * 
     * @return
     *     The branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 
     * @param branch
     *     The Branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * 
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The Category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The comedKNo
     */
    public String getComedKNo() {
        return comedKNo;
    }

    /**
     * 
     * @param comedKNo
     *     The ComedKNo
     */
    public void setComedKNo(String comedKNo) {
        this.comedKNo = comedKNo;
    }

    /**
     * 
     * @return
     *     The comedKRank
     */
    public Integer getComedKRank() {
        return comedKRank;
    }

    /**
     * 
     * @param comedKRank
     *     The ComedKRank
     */
    public void setComedKRank(Integer comedKRank) {
        this.comedKRank = comedKRank;
    }

    /**
     * 
     * @return
     *     The course
     */
    public String getCourse() {
        return course;
    }

    /**
     * 
     * @param course
     *     The Course
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * 
     * @return
     *     The entranceExam
     */
    public String getEntranceExam() {
        return entranceExam;
    }

    /**
     * 
     * @param entranceExam
     *     The EntranceExam
     */
    public void setEntranceExam(String entranceExam) {
        this.entranceExam = entranceExam;
    }

    /**
     * 
     * @return
     *     The quota
     */
    public String getQuota() {
        return quota;
    }

    /**
     * 
     * @param quota
     *     The Quota
     */
    public void setQuota(String quota) {
        this.quota = quota;
    }

    /**
     * 
     * @return
     *     The sem
     */
    public String getSem() {
        return sem;
    }

    /**
     * 
     * @param sem
     *     The Sem
     */
    public void setSem(String sem) {
        this.sem = sem;
    }

}
