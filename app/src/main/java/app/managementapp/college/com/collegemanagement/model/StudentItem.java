package app.managementapp.college.com.collegemanagement.model;

import android.util.Log;

/**
 * Created by new on 4/12/2016.
 */
public class StudentItem {



    String cardNo;
    String code;
    String dateOfBirth;
    String firstName;
    String fullName;
    String gender;
    String ID;
    String lastName;
    String MGUID;
    String middleName;
    String address;
    String email;
    String phone;
    String marks;
    String maxMarks;
    String isEditable;
    boolean present = true;
    String TransactionID;

    public  StudentItem(
            String cardNo, String code, String dateOfBirth, String firstName, String fullName, String gender, String ID, String lastName, String MGUID, String middleName, String address, String email, String phone, String TransactionID, String present, int xx){

        this.cardNo = cardNo;
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.fullName = fullName;
        this.gender = gender;
        this.ID = ID;
        this.lastName = lastName;
        this.MGUID = MGUID;
        this.middleName = middleName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.TransactionID = TransactionID;
        if(present != null && present.equals("false")) this.present = false;
    }


    public  StudentItem(
            String cardNo, String code, String dateOfBirth, String firstName, String fullName, String gender, String ID, String lastName, String MGUID, String middleName, String address, String email, String phone, String marks, String maxMarks){

        this.cardNo = cardNo;
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.fullName = fullName;
        this.gender = gender;
        this.ID = ID;
        this.lastName = lastName;
        this.MGUID = MGUID;
        this.middleName = middleName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.marks = marks;
        this.maxMarks = maxMarks;
    }

    public  StudentItem(
            String cardNo, String code, String dateOfBirth, String firstName, String fullName, String gender, String ID, String lastName, String MGUID, String middleName, String address, String email, String phone, String marks, String maxMarks, String isEditable){

        this.cardNo = cardNo;
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.fullName = fullName;
        this.gender = gender;
        this.ID = ID;
        this.lastName = lastName;
        this.MGUID = MGUID;
        this.middleName = middleName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.marks = marks;
        this.maxMarks = maxMarks;
        this.isEditable = isEditable;
    }


    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMGUID() {
        return MGUID;
    }

    public void setMGUID(String MGUID) {
        this.MGUID = MGUID;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTransactionID(String TransactionID) {
        this.TransactionID = TransactionID;
    }
    public String getTransactionID() {
        return TransactionID;
    }

    public boolean getPresent() {
        return present;
    }

    public void setPresent(boolean present) { this.present = present; }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        Log.d("yes", "setMarks: " + marks);
        this.marks = marks;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }


    public void setIsEditable(String isEditable) {
        this.isEditable = isEditable;
    }

    public String getIsEditable() {
        return isEditable;
    }

}
