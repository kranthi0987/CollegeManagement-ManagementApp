
package app.managementapp.college.com.collegemanagement.api.AcademicCalender;


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
    @SerializedName("AccademicYear")
    @Expose
    private Integer accademicYear;
    @SerializedName("Branch")
    @Expose
    private Object branch;
    @SerializedName("BranchID")
    @Expose
    private Integer branchID;
    @SerializedName("Course")
    @Expose
    private String course;
    @SerializedName("CourseID")
    @Expose
    private Integer courseID;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("Event")
    @Expose
    private String event;
    @SerializedName("EventType")
    @Expose
    private String eventType;
    @SerializedName("Sem")
    @Expose
    private String sem;
    @SerializedName("StartDate")
    @Expose
    private String startDate;

    protected DataList(Parcel in) {
        course = in.readString();
        endDate = in.readString();
        event = in.readString();
        eventType = in.readString();
        sem = in.readString();
        startDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(course);
        dest.writeString(endDate);
        dest.writeString(event);
        dest.writeString(eventType);
        dest.writeString(sem);
        dest.writeString(startDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 
     * @return
     *     The accademicYear
     */
    public Integer getAccademicYear() {
        return accademicYear;
    }

    /**
     * 
     * @param accademicYear
     *     The AccademicYear
     */
    public void setAccademicYear(Integer accademicYear) {
        this.accademicYear = accademicYear;
    }

    /**
     * 
     * @return
     *     The branch
     */
    public Object getBranch() {
        return branch;
    }

    /**
     * 
     * @param branch
     *     The Branch
     */
    public void setBranch(Object branch) {
        this.branch = branch;
    }

    /**
     * 
     * @return
     *     The branchID
     */
    public Integer getBranchID() {
        return branchID;
    }

    /**
     * 
     * @param branchID
     *     The BranchID
     */
    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
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
     *     The courseID
     */
    public Integer getCourseID() {
        return courseID;
    }

    /**
     * 
     * @param courseID
     *     The CourseID
     */
    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
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
     *     The event
     */
    public String getEvent() {
        return event;
    }

    /**
     * 
     * @param event
     *     The Event
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * 
     * @return
     *     The eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * 
     * @param eventType
     *     The EventType
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
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

}
