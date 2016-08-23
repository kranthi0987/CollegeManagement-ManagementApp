
package app.managementapp.college.com.collegemanagement.api.TimeTableSchedule;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("AdTeacherID")
    @Expose
    private Integer adTeacherID;
    @SerializedName("ClassDate")
    @Expose
    private String classDate;
    @SerializedName("ClassSchedID")
    @Expose
    private Integer classSchedID;
    @SerializedName("Course")
    @Expose
    private String course;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("EndTime")
    @Expose
    private String endTime;
    @SerializedName("Place")
    @Expose
    private String place;
    @SerializedName("Section")
    @Expose
    private String section;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("StartTime")
    @Expose
    private String startTime;
    @SerializedName("Title")
    @Expose
    private String title;

    /**
     * 
     * @return
     *     The adTeacherID
     */
    public Integer getAdTeacherID() {
        return adTeacherID;
    }

    /**
     * 
     * @param adTeacherID
     *     The AdTeacherID
     */
    public void setAdTeacherID(Integer adTeacherID) {
        this.adTeacherID = adTeacherID;
    }

    /**
     * 
     * @return
     *     The classDate
     */
    public String getClassDate() {
        return classDate;
    }

    /**
     * 
     * @param classDate
     *     The ClassDate
     */
    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    /**
     * 
     * @return
     *     The classSchedID
     */
    public Integer getClassSchedID() {
        return classSchedID;
    }

    /**
     * 
     * @param classSchedID
     *     The ClassSchedID
     */
    public void setClassSchedID(Integer classSchedID) {
        this.classSchedID = classSchedID;
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
     *     The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate
     *     The EndDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The EndTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return
     *     The place
     */
    public String getPlace() {
        return place;
    }

    /**
     * 
     * @param place
     *     The Place
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * 
     * @return
     *     The section
     */
    public String getSection() {
        return section;
    }

    /**
     * 
     * @param section
     *     The Section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * 
     * @return
     *     The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     *     The StartDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The StartTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
