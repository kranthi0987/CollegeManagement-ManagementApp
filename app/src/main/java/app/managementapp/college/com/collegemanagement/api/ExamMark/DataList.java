
package app.managementapp.college.com.collegemanagement.api.ExamMark;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Batch")
    @Expose
    private Integer batch;
    @SerializedName("Credit")
    @Expose
    private Integer credit;
    @SerializedName("ExternalMark")
    @Expose
    private Integer externalMark;
    @SerializedName("Grade")
    @Expose
    private Object grade;
    @SerializedName("GradePoint")
    @Expose
    private Integer gradePoint;
    @SerializedName("InternalMark")
    @Expose
    private Integer internalMark;
    @SerializedName("MaxMark")
    @Expose
    private Integer maxMark;
    @SerializedName("Result")
    @Expose
    private Boolean result;
    @SerializedName("ResultDate")
    @Expose
    private String resultDate;
    @SerializedName("Sem")
    @Expose
    private String sem;
    @SerializedName("TotalMark")
    @Expose
    private Integer totalMark;

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
     *     The iD
     */
    public Integer getID() {
        return iD;
    }

    /**
     * 
     * @param iD
     *     The ID
     */
    public void setID(Integer iD) {
        this.iD = iD;
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
     *     The batch
     */
    public Integer getBatch() {
        return batch;
    }

    /**
     * 
     * @param batch
     *     The Batch
     */
    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    /**
     * 
     * @return
     *     The credit
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * 
     * @param credit
     *     The Credit
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * 
     * @return
     *     The externalMark
     */
    public Integer getExternalMark() {
        return externalMark;
    }

    /**
     * 
     * @param externalMark
     *     The ExternalMark
     */
    public void setExternalMark(Integer externalMark) {
        this.externalMark = externalMark;
    }

    /**
     * 
     * @return
     *     The grade
     */
    public Object getGrade() {
        return grade;
    }

    /**
     * 
     * @param grade
     *     The Grade
     */
    public void setGrade(Object grade) {
        this.grade = grade;
    }

    /**
     * 
     * @return
     *     The gradePoint
     */
    public Integer getGradePoint() {
        return gradePoint;
    }

    /**
     * 
     * @param gradePoint
     *     The GradePoint
     */
    public void setGradePoint(Integer gradePoint) {
        this.gradePoint = gradePoint;
    }

    /**
     * 
     * @return
     *     The internalMark
     */
    public Integer getInternalMark() {
        return internalMark;
    }

    /**
     * 
     * @param internalMark
     *     The InternalMark
     */
    public void setInternalMark(Integer internalMark) {
        this.internalMark = internalMark;
    }

    /**
     * 
     * @return
     *     The maxMark
     */
    public Integer getMaxMark() {
        return maxMark;
    }

    /**
     * 
     * @param maxMark
     *     The MaxMark
     */
    public void setMaxMark(Integer maxMark) {
        this.maxMark = maxMark;
    }

    /**
     * 
     * @return
     *     The result
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The Result
     */
    public void setResult(Boolean result) {
        this.result = result;
    }

    /**
     * 
     * @return
     *     The resultDate
     */
    public String getResultDate() {
        return resultDate;
    }

    /**
     * 
     * @param resultDate
     *     The ResultDate
     */
    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
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

    /**
     * 
     * @return
     *     The totalMark
     */
    public Integer getTotalMark() {
        return totalMark;
    }

    /**
     * 
     * @param totalMark
     *     The TotalMark
     */
    public void setTotalMark(Integer totalMark) {
        this.totalMark = totalMark;
    }

}
