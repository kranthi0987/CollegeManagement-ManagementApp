
package app.managementapp.college.com.collegemanagement.api.StaffLoginAbsenteesSummary;


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
    @SerializedName("Absentees")
    @Expose
    private Integer absentees;
    @SerializedName("Active")
    @Expose
    private Integer active;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("CategoryID")
    @Expose
    private String categoryID;
    @SerializedName("CurrentLevel")
    @Expose
    private Integer currentLevel;
    @SerializedName("IsChildAvailable")
    @Expose
    private Boolean isChildAvailable;
    @SerializedName("OnLeave")
    @Expose
    private Integer onLeave;
    @SerializedName("Present")
    @Expose
    private Integer present;

    protected DataList(Parcel in) {
        category = in.readString();
        categoryID = in.readString();
    }

    /**
     * 
     * @return
     *     The absentees
     */
    public Integer getAbsentees() {
        return absentees;
    }

    /**
     * 
     * @param absentees
     *     The Absentees
     */
    public void setAbsentees(Integer absentees) {
        this.absentees = absentees;
    }

    /**
     * 
     * @return
     *     The active
     */
    public Integer getActive() {
        return active;
    }

    /**
     * 
     * @param active
     *     The Active
     */
    public void setActive(Integer active) {
        this.active = active;
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
     *     The categoryID
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * 
     * @param categoryID
     *     The CategoryID
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * 
     * @return
     *     The currentLevel
     */
    public Integer getCurrentLevel() {
        return currentLevel;
    }

    /**
     * 
     * @param currentLevel
     *     The CurrentLevel
     */
    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * 
     * @return
     *     The isChildAvailable
     */
    public Boolean getIsChildAvailable() {
        return isChildAvailable;
    }

    /**
     * 
     * @param isChildAvailable
     *     The IsChildAvailable
     */
    public void setIsChildAvailable(Boolean isChildAvailable) {
        this.isChildAvailable = isChildAvailable;
    }

    /**
     * 
     * @return
     *     The onLeave
     */
    public Integer getOnLeave() {
        return onLeave;
    }

    /**
     * 
     * @param onLeave
     *     The OnLeave
     */
    public void setOnLeave(Integer onLeave) {
        this.onLeave = onLeave;
    }

    /**
     * 
     * @return
     *     The present
     */
    public Integer getPresent() {
        return present;
    }

    /**
     * 
     * @param present
     *     The Present
     */
    public void setPresent(Integer present) {
        this.present = present;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeString(categoryID);
    }
}
