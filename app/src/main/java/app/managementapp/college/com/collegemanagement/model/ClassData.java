package app.managementapp.college.com.collegemanagement.model;

/**
 * Created by new on 4/9/2016.
 */
public class ClassData {
    public ClassData(){

    }
    public ClassData(String adTeacherID, String classDate, String classSchedID,
                     String course, String endDate, String endTime, String place,
                     String section, String startDate, String startTime, String title,
                     String intentClassDate, String intentStartTime, String intentEndTime){
        this.adTeacherID = adTeacherID;
        this.classDate =  classDate;
        this.classSchedID =  classSchedID;
        this.course =  course;
        this.endDate =  endDate;
        this.endTime =  endTime;
        this.place =  place;
        this.section =  section;
        this.startDate =  startDate;
        this.startTime =  startTime;
        this.title =  title;
        this.intentClassDate =  intentClassDate;
        this.intentStartTime =  intentStartTime;
        this.intentEndTime =  intentEndTime;


    }
    String adTeacherID;
    String classDate;
    String classSchedID;
    String course;
    String endDate;
    String endTime;
    String place;
    String section;
    String startDate;
    String startTime;
    String title;
    String intentClassDate;
    String intentStartTime;
    String intentEndTime;

    public String getAdTeacherID() {
        return adTeacherID;
    }

    public void setAdTeacherID(String adTeacherID) {
        this.adTeacherID = adTeacherID;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getClassSchedID() {
        return classSchedID;
    }

    public void setClassSchedID(String classSchedID) {
        this.classSchedID = classSchedID;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntentClassDate() {
        return intentClassDate;
    }

    public void setIntentClassDate(String intentClassDate) {
        this.intentClassDate = intentClassDate;
    }

    public String getIntentStartTime() {
        return intentStartTime;
    }

    public void setIntentStartTime(String intentStartTime) {
        this.intentStartTime = intentStartTime;
    }

    public String getIntentEndTime() {
        return intentEndTime;
    }

    public void setIntentEndTime(String intentEndTime) {
        this.intentEndTime = intentEndTime;
    }
}
