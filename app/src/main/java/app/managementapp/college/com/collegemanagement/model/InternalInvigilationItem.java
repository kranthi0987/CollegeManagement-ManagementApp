package app.managementapp.college.com.collegemanagement.model;

import java.util.Date;

/**
 * Created by new on 4/21/2016.
 */
public class InternalInvigilationItem {

    public InternalInvigilationItem(){}

    public InternalInvigilationItem(Date ExamDate, String examName, String roomCode, String roomDescription,
                                    String roomID, String session, String sessionID, String subjects,
                                    String intentClassDate, String intentStartTime, String intentEndTime){

        this.ExamDate = ExamDate;
        this.ExamName = examName;
        this.RoomCode = roomCode;
        this.RoomDescription = roomDescription;
        this.RoomID = roomID;
        this.Session = session;
        this.SessionID = sessionID;
        this.Subjects = subjects;
        this.intentClassDate =  intentClassDate;
        this.intentStartTime =  intentStartTime;
        this.intentEndTime =  intentEndTime;

    }
    public Date getExamDate() {
        return ExamDate;
    }

    public void setExamDate(Date examDate) {
        this.ExamDate = examDate;
    }


    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String examName) {
        this.ExamName = examName;
    }

    public String getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(String roomCode) {
        this.RoomCode = roomCode;
    }

    public String getRoomDescription() {
        return RoomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.RoomDescription = roomDescription;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        this.RoomID = roomID;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        this.Session = session;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        this.SessionID = sessionID;
    }

    public String getSubjects() {
        return Subjects;
    }

    public void setSubjects(String subjects) {
        this.Subjects = subjects;
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

    private Date ExamDate;
    private String ExamName;
    private String RoomCode;
    private String RoomDescription;
    private String RoomID;
    private String Session;
    private String SessionID;
    private String Subjects;
    private String intentClassDate;
    private String intentStartTime;
    private String intentEndTime;

}
