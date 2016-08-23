
package app.managementapp.college.com.collegemanagement.api.StudentPersonalDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataList {

    @SerializedName("Addressal")
    @Expose
    private Object addressal;
    @SerializedName("CardNo")
    @Expose
    private Object cardNo;
    @SerializedName("Code")
    @Expose
    private Object code;
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
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
    private String lastName;
    @SerializedName("MGUID")
    @Expose
    private String mGUID;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("Photo")
    @Expose
    private Object photo;
    @SerializedName("AdmissionYear")
    @Expose
    private String admissionYear;
    @SerializedName("ApplicationNo")
    @Expose
    private String applicationNo;
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
    private String fathersName;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("MothersName")
    @Expose
    private String mothersName;
    @SerializedName("PermanentAddress")
    @Expose
    private PermanentAddress permanentAddress;
    @SerializedName("RegNomenclature")
    @Expose
    private String regNomenclature;
    @SerializedName("RegistrationNo")
    @Expose
    private String registrationNo;
    @SerializedName("RollNo")
    @Expose
    private Object rollNo;
    @SerializedName("SectionName")
    @Expose
    private Object sectionName;
    @SerializedName("SemID")
    @Expose
    private Integer semID;
    @SerializedName("Semester")
    @Expose
    private String semester;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("SyllabusYear")
    @Expose
    private String syllabusYear;
    @SerializedName("TermNomenclature")
    @Expose
    private String termNomenclature;
    @SerializedName("UniversityAddress")
    @Expose
    private String universityAddress;
    @SerializedName("UniversityName")
    @Expose
    private String universityName;
    @SerializedName("isGrade")
    @Expose
    private Integer isGrade;

    /**
     * 
     * @return
     *     The addressal
     */
    public Object getAddressal() {
        return addressal;
    }

    /**
     * 
     * @param addressal
     *     The Addressal
     */
    public void setAddressal(Object addressal) {
        this.addressal = addressal;
    }

    /**
     * 
     * @return
     *     The cardNo
     */
    public Object getCardNo() {
        return cardNo;
    }

    /**
     * 
     * @param cardNo
     *     The CardNo
     */
    public void setCardNo(Object cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 
     * @return
     *     The code
     */
    public Object getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The Code
     */
    public void setCode(Object code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * 
     * @param dateOfBirth
     *     The DateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The FirstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The FullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     * @return
     *     The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     *     The Gender
     */
    public void setGender(String gender) {
        this.gender = gender;
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
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The LastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The mGUID
     */
    public String getMGUID() {
        return mGUID;
    }

    /**
     * 
     * @param mGUID
     *     The MGUID
     */
    public void setMGUID(String mGUID) {
        this.mGUID = mGUID;
    }

    /**
     * 
     * @return
     *     The middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 
     * @param middleName
     *     The MiddleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * 
     * @return
     *     The photo
     */
    public Object getPhoto() {
        return photo;
    }

    /**
     * 
     * @param photo
     *     The Photo
     */
    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    /**
     * 
     * @return
     *     The admissionYear
     */
    public String getAdmissionYear() {
        return admissionYear;
    }

    /**
     * 
     * @param admissionYear
     *     The AdmissionYear
     */
    public void setAdmissionYear(String admissionYear) {
        this.admissionYear = admissionYear;
    }

    /**
     * 
     * @return
     *     The applicationNo
     */
    public String getApplicationNo() {
        return applicationNo;
    }

    /**
     * 
     * @param applicationNo
     *     The ApplicationNo
     */
    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    /**
     * 
     * @return
     *     The bloodGroup
     */
    public Object getBloodGroup() {
        return bloodGroup;
    }

    /**
     * 
     * @param bloodGroup
     *     The BloodGroup
     */
    public void setBloodGroup(Object bloodGroup) {
        this.bloodGroup = bloodGroup;
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
     *     The branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * 
     * @param branchName
     *     The BranchName
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * 
     * @return
     *     The college
     */
    public College getCollege() {
        return college;
    }

    /**
     * 
     * @param college
     *     The College
     */
    public void setCollege(College college) {
        this.college = college;
    }

    /**
     * 
     * @return
     *     The communicationAddress
     */
    public CommunicationAddress getCommunicationAddress() {
        return communicationAddress;
    }

    /**
     * 
     * @param communicationAddress
     *     The CommunicationAddress
     */
    public void setCommunicationAddress(CommunicationAddress communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    /**
     * 
     * @return
     *     The country
     */
    public Object getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The Country
     */
    public void setCountry(Object country) {
        this.country = country;
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
     *     The courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 
     * @param courseName
     *     The CourseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The fatherOccupation
     */
    public Object getFatherOccupation() {
        return fatherOccupation;
    }

    /**
     * 
     * @param fatherOccupation
     *     The FatherOccupation
     */
    public void setFatherOccupation(Object fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    /**
     * 
     * @return
     *     The fathersName
     */
    public String getFathersName() {
        return fathersName;
    }

    /**
     * 
     * @param fathersName
     *     The FathersName
     */
    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    /**
     * 
     * @return
     *     The mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 
     * @param mobile
     *     The Mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 
     * @return
     *     The mothersName
     */
    public String getMothersName() {
        return mothersName;
    }

    /**
     * 
     * @param mothersName
     *     The MothersName
     */
    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    /**
     * 
     * @return
     *     The permanentAddress
     */
    public PermanentAddress getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * 
     * @param permanentAddress
     *     The PermanentAddress
     */
    public void setPermanentAddress(PermanentAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    /**
     * 
     * @return
     *     The regNomenclature
     */
    public String getRegNomenclature() {
        return regNomenclature;
    }

    /**
     * 
     * @param regNomenclature
     *     The RegNomenclature
     */
    public void setRegNomenclature(String regNomenclature) {
        this.regNomenclature = regNomenclature;
    }

    /**
     * 
     * @return
     *     The registrationNo
     */
    public String getRegistrationNo() {
        return registrationNo;
    }

    /**
     * 
     * @param registrationNo
     *     The RegistrationNo
     */
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    /**
     * 
     * @return
     *     The rollNo
     */
    public Object getRollNo() {
        return rollNo;
    }

    /**
     * 
     * @param rollNo
     *     The RollNo
     */
    public void setRollNo(Object rollNo) {
        this.rollNo = rollNo;
    }

    /**
     * 
     * @return
     *     The sectionName
     */
    public Object getSectionName() {
        return sectionName;
    }

    /**
     * 
     * @param sectionName
     *     The SectionName
     */
    public void setSectionName(Object sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * 
     * @return
     *     The semID
     */
    public Integer getSemID() {
        return semID;
    }

    /**
     * 
     * @param semID
     *     The SemID
     */
    public void setSemID(Integer semID) {
        this.semID = semID;
    }

    /**
     * 
     * @return
     *     The semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * 
     * @param semester
     *     The Semester
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The Status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The syllabusYear
     */
    public String getSyllabusYear() {
        return syllabusYear;
    }

    /**
     * 
     * @param syllabusYear
     *     The SyllabusYear
     */
    public void setSyllabusYear(String syllabusYear) {
        this.syllabusYear = syllabusYear;
    }

    /**
     * 
     * @return
     *     The termNomenclature
     */
    public String getTermNomenclature() {
        return termNomenclature;
    }

    /**
     * 
     * @param termNomenclature
     *     The TermNomenclature
     */
    public void setTermNomenclature(String termNomenclature) {
        this.termNomenclature = termNomenclature;
    }

    /**
     * 
     * @return
     *     The universityAddress
     */
    public String getUniversityAddress() {
        return universityAddress;
    }

    /**
     * 
     * @param universityAddress
     *     The UniversityAddress
     */
    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    /**
     * 
     * @return
     *     The universityName
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * 
     * @param universityName
     *     The UniversityName
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * 
     * @return
     *     The isGrade
     */
    public Integer getIsGrade() {
        return isGrade;
    }

    /**
     * 
     * @param isGrade
     *     The isGrade
     */
    public void setIsGrade(Integer isGrade) {
        this.isGrade = isGrade;
    }

}
