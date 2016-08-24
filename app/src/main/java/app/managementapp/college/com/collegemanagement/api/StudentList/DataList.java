
package app.managementapp.college.com.collegemanagement.api.StudentList;


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
    @SerializedName("Addressal")
    @Expose
    private String addressal;
    @SerializedName("CardNo")
    @Expose
    private Object cardNo;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("FirstName")
    @Expose
    private Object firstName;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("LastName")
    @Expose
    private Object lastName;
    @SerializedName("MGUID")
    @Expose
    private String mGUID;
    @SerializedName("MiddleName")
    @Expose
    private Object middleName;
    @SerializedName("Photo")
    @Expose
    private Object photo;
    @SerializedName("AdmissionYear")
    @Expose
    private Object admissionYear;
    @SerializedName("ApplicationNo")
    @Expose
    private Object applicationNo;
    @SerializedName("BloodGroup")
    @Expose
    private Object bloodGroup;
    @SerializedName("BranchID")
    @Expose
    private Integer branchID;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("College")
    @Expose
    private College college;
    @SerializedName("CommunicationAddress")
    @Expose
    private CommunicationAddress communicationAddress;
    @SerializedName("Country")
    @Expose
    private Object country;
    @SerializedName("CourseID")
    @Expose
    private Integer courseID;
    @SerializedName("CourseName")
    @Expose
    private String courseName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("FatherOccupation")
    @Expose
    private Object fatherOccupation;
    @SerializedName("FathersName")
    @Expose
    private Object fathersName;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("MothersName")
    @Expose
    private Object mothersName;
    @SerializedName("PermanentAddress")
    @Expose
    private PermanentAddress permanentAddress;
    @SerializedName("RegNomenclature")
    @Expose
    private Object regNomenclature;
    @SerializedName("RegistrationNo")
    @Expose
    private Object registrationNo;
    @SerializedName("RollNo")
    @Expose
    private Object rollNo;
    @SerializedName("SectionName")
    @Expose
    private String sectionName;
    @SerializedName("SemID")
    @Expose
    private Object semID;
    @SerializedName("Semester")
    @Expose
    private Object semester;
    @SerializedName("Status")
    @Expose
    private Object status;
    @SerializedName("SyllabusYear")
    @Expose
    private Object syllabusYear;
    @SerializedName("TermNomenclature")
    @Expose
    private Object termNomenclature;
    @SerializedName("UniversityAddress")
    @Expose
    private Object universityAddress;
    @SerializedName("UniversityName")
    @Expose
    private Object universityName;
    @SerializedName("isGrade")
    @Expose
    private Integer isGrade;

    protected DataList(Parcel in) {
        addressal = in.readString();
        code = in.readString();
        dateOfBirth = in.readString();
        fullName = in.readString();
        gender = in.readString();
        mGUID = in.readString();
        branchName = in.readString();
        courseName = in.readString();
        email = in.readString();
        mobile = in.readString();
        permanentAddress = in.readParcelable(PermanentAddress.class.getClassLoader());
        sectionName = in.readString();
    }

    /**
     * @return The addressal
     */
    public String getAddressal() {
        return addressal;
    }

    /**
     * @param addressal The Addressal
     */
    public void setAddressal(String addressal) {
        this.addressal = addressal;
    }

    /**
     * @return The cardNo
     */
    public Object getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo The CardNo
     */
    public void setCardNo(Object cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The Code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth The DateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return The firstName
     */
    public Object getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The FirstName
     */
    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName The FullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender The Gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return The iD
     */
    public Integer getID() {
        return iD;
    }

    /**
     * @param iD The ID
     */
    public void setID(Integer iD) {
        this.iD = iD;
    }

    /**
     * @return The lastName
     */
    public Object getLastName() {
        return lastName;
    }

    /**
     * @param lastName The LastName
     */
    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The mGUID
     */
    public String getMGUID() {
        return mGUID;
    }

    /**
     * @param mGUID The MGUID
     */
    public void setMGUID(String mGUID) {
        this.mGUID = mGUID;
    }

    /**
     * @return The middleName
     */
    public Object getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName The MiddleName
     */
    public void setMiddleName(Object middleName) {
        this.middleName = middleName;
    }

    /**
     * @return The photo
     */
    public Object getPhoto() {
        return photo;
    }

    /**
     * @param photo The Photo
     */
    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    /**
     * @return The admissionYear
     */
    public Object getAdmissionYear() {
        return admissionYear;
    }

    /**
     * @param admissionYear The AdmissionYear
     */
    public void setAdmissionYear(Object admissionYear) {
        this.admissionYear = admissionYear;
    }

    /**
     * @return The applicationNo
     */
    public Object getApplicationNo() {
        return applicationNo;
    }

    /**
     * @param applicationNo The ApplicationNo
     */
    public void setApplicationNo(Object applicationNo) {
        this.applicationNo = applicationNo;
    }

    /**
     * @return The bloodGroup
     */
    public Object getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param bloodGroup The BloodGroup
     */
    public void setBloodGroup(Object bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * @return The branchID
     */
    public Integer getBranchID() {
        return branchID;
    }

    /**
     * @param branchID The BranchID
     */
    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    /**
     * @return The branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName The BranchName
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return The college
     */
    public College getCollege() {
        return college;
    }

    /**
     * @param college The College
     */
    public void setCollege(College college) {
        this.college = college;
    }

    /**
     * @return The communicationAddress
     */
    public CommunicationAddress getCommunicationAddress() {
        return communicationAddress;
    }

    /**
     * @param communicationAddress The CommunicationAddress
     */
    public void setCommunicationAddress(CommunicationAddress communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    /**
     * @return The country
     */
    public Object getCountry() {
        return country;
    }

    /**
     * @param country The Country
     */
    public void setCountry(Object country) {
        this.country = country;
    }

    /**
     * @return The courseID
     */
    public Integer getCourseID() {
        return courseID;
    }

    /**
     * @param courseID The CourseID
     */
    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    /**
     * @return The courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName The CourseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The fatherOccupation
     */
    public Object getFatherOccupation() {
        return fatherOccupation;
    }

    /**
     * @param fatherOccupation The FatherOccupation
     */
    public void setFatherOccupation(Object fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    /**
     * @return The fathersName
     */
    public Object getFathersName() {
        return fathersName;
    }

    /**
     * @param fathersName The FathersName
     */
    public void setFathersName(Object fathersName) {
        this.fathersName = fathersName;
    }

    /**
     * @return The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile The Mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return The mothersName
     */
    public Object getMothersName() {
        return mothersName;
    }

    /**
     * @param mothersName The MothersName
     */
    public void setMothersName(Object mothersName) {
        this.mothersName = mothersName;
    }

    /**
     * @return The permanentAddress
     */
    public PermanentAddress getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * @param permanentAddress The PermanentAddress
     */
    public void setPermanentAddress(PermanentAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    /**
     * @return The regNomenclature
     */
    public Object getRegNomenclature() {
        return regNomenclature;
    }

    /**
     * @param regNomenclature The RegNomenclature
     */
    public void setRegNomenclature(Object regNomenclature) {
        this.regNomenclature = regNomenclature;
    }

    /**
     * @return The registrationNo
     */
    public Object getRegistrationNo() {
        return registrationNo;
    }

    /**
     * @param registrationNo The RegistrationNo
     */
    public void setRegistrationNo(Object registrationNo) {
        this.registrationNo = registrationNo;
    }

    /**
     * @return The rollNo
     */
    public Object getRollNo() {
        return rollNo;
    }

    /**
     * @param rollNo The RollNo
     */
    public void setRollNo(Object rollNo) {
        this.rollNo = rollNo;
    }

    /**
     * @return The sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * @param sectionName The SectionName
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * @return The semID
     */
    public Object getSemID() {
        return semID;
    }

    /**
     * @param semID The SemID
     */
    public void setSemID(Object semID) {
        this.semID = semID;
    }

    /**
     * @return The semester
     */
    public Object getSemester() {
        return semester;
    }

    /**
     * @param semester The Semester
     */
    public void setSemester(Object semester) {
        this.semester = semester;
    }

    /**
     * @return The status
     */
    public Object getStatus() {
        return status;
    }

    /**
     * @param status The Status
     */
    public void setStatus(Object status) {
        this.status = status;
    }

    /**
     * @return The syllabusYear
     */
    public Object getSyllabusYear() {
        return syllabusYear;
    }

    /**
     * @param syllabusYear The SyllabusYear
     */
    public void setSyllabusYear(Object syllabusYear) {
        this.syllabusYear = syllabusYear;
    }

    /**
     * @return The termNomenclature
     */
    public Object getTermNomenclature() {
        return termNomenclature;
    }

    /**
     * @param termNomenclature The TermNomenclature
     */
    public void setTermNomenclature(Object termNomenclature) {
        this.termNomenclature = termNomenclature;
    }

    /**
     * @return The universityAddress
     */
    public Object getUniversityAddress() {
        return universityAddress;
    }

    /**
     * @param universityAddress The UniversityAddress
     */
    public void setUniversityAddress(Object universityAddress) {
        this.universityAddress = universityAddress;
    }

    /**
     * @return The universityName
     */
    public Object getUniversityName() {
        return universityName;
    }

    /**
     * @param universityName The UniversityName
     */
    public void setUniversityName(Object universityName) {
        this.universityName = universityName;
    }

    /**
     * @return The isGrade
     */
    public Integer getIsGrade() {
        return isGrade;
    }

    /**
     * @param isGrade The isGrade
     */
    public void setIsGrade(Integer isGrade) {
        this.isGrade = isGrade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addressal);
        dest.writeString(code);
        dest.writeString(dateOfBirth);
        dest.writeString(fullName);
        dest.writeString(gender);
        dest.writeString(mGUID);
        dest.writeString(branchName);
        dest.writeString(courseName);
        dest.writeString(email);
        dest.writeString(mobile);
        dest.writeParcelable(permanentAddress, flags);
        dest.writeString(sectionName);
    }
}
