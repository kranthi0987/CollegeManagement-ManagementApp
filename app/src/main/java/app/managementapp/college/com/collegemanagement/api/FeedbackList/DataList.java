
package app.managementapp.college.app.managementapp.college.com.collegemanagement.api.FeedbackList;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class DataList implements Parcelable {

    private String date;
    private String feedbackPerson;
    private Object fileContent;
    private String fileName;
    private String filePath;
    private String message;
    private String messageTitle;
    private Integer messageType;
    private Integer refID;
    private String reply;
    private Integer studentID;
    private String userType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected DataList(Parcel in) {
        date = in.readString();
        feedbackPerson = in.readString();
        fileName = in.readString();
        filePath = in.readString();
        message = in.readString();
        messageTitle = in.readString();
        reply = in.readString();
        userType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(feedbackPerson);
        dest.writeString(fileName);
        dest.writeString(filePath);
        dest.writeString(message);
        dest.writeString(messageTitle);
        dest.writeString(reply);
        dest.writeString(userType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

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

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The Date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The feedbackPerson
     */
    public String getFeedbackPerson() {
        return feedbackPerson;
    }

    /**
     * 
     * @param feedbackPerson
     *     The FeedbackPerson
     */
    public void setFeedbackPerson(String feedbackPerson) {
        this.feedbackPerson = feedbackPerson;
    }

    /**
     * 
     * @return
     *     The fileContent
     */
    public Object getFileContent() {
        return fileContent;
    }

    /**
     * 
     * @param fileContent
     *     The FileContent
     */
    public void setFileContent(Object fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * 
     * @return
     *     The fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 
     * @param fileName
     *     The FileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 
     * @return
     *     The filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 
     * @param filePath
     *     The FilePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The Message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The messageTitle
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    /**
     * 
     * @param messageTitle
     *     The MessageTitle
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    /**
     * 
     * @return
     *     The messageType
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * 
     * @param messageType
     *     The MessageType
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * 
     * @return
     *     The refID
     */
    public Integer getRefID() {
        return refID;
    }

    /**
     * 
     * @param refID
     *     The RefID
     */
    public void setRefID(Integer refID) {
        this.refID = refID;
    }

    /**
     * 
     * @return
     *     The reply
     */
    public String getReply() {
        return reply;
    }

    /**
     * 
     * @param reply
     *     The Reply
     */
    public void setReply(String reply) {
        this.reply = reply;
    }

    /**
     * 
     * @return
     *     The studentID
     */
    public Integer getStudentID() {
        return studentID;
    }

    /**
     * 
     * @param studentID
     *     The StudentID
     */
    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    /**
     * 
     * @return
     *     The userType
     */
    public String getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "DataList{" +
                "date='" + date + '\'' +
                ", feedbackPerson='" + feedbackPerson + '\'' +
                ", fileContent=" + fileContent +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", message='" + message + '\'' +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageType=" + messageType +
                ", refID=" + refID +
                ", reply='" + reply + '\'' +
                ", studentID=" + studentID +
                ", userType='" + userType + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    /**
     * 
     * @param userType
     *     The UserType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
