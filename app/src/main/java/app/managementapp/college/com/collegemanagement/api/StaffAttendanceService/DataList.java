
package com.collegemanagement.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("NotificationDescription")
    @Expose
    private String notificationDescription;
    @SerializedName("NotificationTitle")
    @Expose
    private String notificationTitle;
    @SerializedName("RefID")
    @Expose
    private Integer refID;
    @SerializedName("SentByGUID")
    @Expose
    private Object sentByGUID;
    @SerializedName("SentByName")
    @Expose
    private Object sentByName;

    /**
     * 
     * @return
     *     The notificationDescription
     */
    public String getNotificationDescription() {
        return notificationDescription;
    }

    /**
     * 
     * @param notificationDescription
     *     The NotificationDescription
     */
    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    /**
     * 
     * @return
     *     The notificationTitle
     */
    public String getNotificationTitle() {
        return notificationTitle;
    }

    /**
     * 
     * @param notificationTitle
     *     The NotificationTitle
     */
    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
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
     *     The sentByGUID
     */
    public Object getSentByGUID() {
        return sentByGUID;
    }

    /**
     * 
     * @param sentByGUID
     *     The SentByGUID
     */
    public void setSentByGUID(Object sentByGUID) {
        this.sentByGUID = sentByGUID;
    }

    /**
     * 
     * @return
     *     The sentByName
     */
    public Object getSentByName() {
        return sentByName;
    }

    /**
     * 
     * @param sentByName
     *     The SentByName
     */
    public void setSentByName(Object sentByName) {
        this.sentByName = sentByName;
    }

}
