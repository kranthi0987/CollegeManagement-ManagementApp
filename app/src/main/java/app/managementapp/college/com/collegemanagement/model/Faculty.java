package app.managementapp.college.com.collegemanagement.model;

/**
 * Created by new on 7/1/2016.
 */
public class Faculty {


    String Code;
    String Department;
    String DepartmentID;
    String Designation;
    String DesignationID;
    String Email;
    String FirstName;
    String ID;
    String LastName;
    String MGUID;
    String MiddleName;
    String Phone;

    public Faculty(String code, String department, String departmentID, String designation, String designationID, String email, String firstName, String id, String lastName, String mguid, String middleName, String phone) {
        Code = code;
        Department = department;
        DepartmentID = departmentID;
        Designation = designation;
        DesignationID = designationID;
        Email = email;
        FirstName = firstName;
        ID = id;
        LastName = lastName;
        MGUID = mguid;
        MiddleName = middleName;
        Phone = phone;
    }


    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getDesignationID() {
        return DesignationID;
    }

    public void setDesignationID(String designationID) {
        DesignationID = designationID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMGUID() {
        return MGUID;
    }

    public void setMGUID(String MGUID) {
        this.MGUID = MGUID;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

}
