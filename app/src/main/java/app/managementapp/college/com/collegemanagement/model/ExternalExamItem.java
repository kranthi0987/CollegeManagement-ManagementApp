package app.managementapp.college.com.collegemanagement.model;

import android.util.Log;

import java.util.Date;

/**
 * Created by new on 4/27/2016.
 */
public class ExternalExamItem {


    private String EndTime;
    private String ExamID;
    private String ExamName;
    private Date ScheduleDate;
    private String Section;
    private String Semester;
    private String StartTime;
    private String SubjectCode;
    private String SubjectName;

    public ExternalExamItem(String EndTime, String ExamID, String ExamName, Date ScheduleDate, String Section, String Semester, String StartTime, String SubjectCode, String SubjectName) {

        Log.d("ExternalExamItem", "ExternalExamItem: yesss " + this.EndTime + " = " + EndTime);
        this.EndTime = EndTime;
        this.ExamID = ExamID;
        this.ExamName = ExamName;
        this.ScheduleDate = ScheduleDate;
        this.Section = Section;
        this.Semester = Semester;
        this.StartTime = StartTime;
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
    }

    public ExternalExamItem() {

    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getExamID() {
        return ExamID;
    }

    public void setExamID(String examID) {
        ExamID = examID;
    }

    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String examName) {
        ExamName = examName;
    }

    public Date getScheduleDate() {
        return ScheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        ScheduleDate = scheduleDate;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        SubjectCode = subjectCode;
    }


}
