package app.managementapp.college.com.collegemanagement.api;


import app.managementapp.college.com.collegemanagement.api.AcademicCalender.AcademicCalenderResponse;
import app.managementapp.college.com.collegemanagement.api.AdmissionDetails.AdmissionDetailsResponse;
import app.managementapp.college.com.collegemanagement.api.AttendanceDetails.AttendanceDetailsResponse;
import app.managementapp.college.com.collegemanagement.api.BranchOrCycle.BranchOrCycleResponse;
import app.managementapp.college.com.collegemanagement.api.CollegeProfile.CollegeProfileResponse;
import app.managementapp.college.com.collegemanagement.api.CourseFeedback.CourseFeedbackResponse;
import app.managementapp.college.com.collegemanagement.api.CourseList.CourseListResponse;
import app.managementapp.college.com.collegemanagement.api.ExamMark.ExamMarkResponse;
import app.managementapp.college.com.collegemanagement.api.FeePaymentDetails.FeePaymentDetailsResponse;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.FeedbackListResponse;
import app.managementapp.college.com.collegemanagement.api.Profiles.University.UniversityProfileResponse;
import app.managementapp.college.com.collegemanagement.api.QualificationDetails.QualificationDetailsResponse;
import app.managementapp.college.com.collegemanagement.api.Semester.SemesterResponse;
import app.managementapp.college.com.collegemanagement.api.Staff.Department.DepartmentResponse;
import app.managementapp.college.com.collegemanagement.api.Staff.StaffAttendance.StaffAttendanceResponse;
import app.managementapp.college.com.collegemanagement.api.Staff.StaffFeedback.StaffFeedbackResponse;
import app.managementapp.college.com.collegemanagement.api.Staff.StaffLeavesApplied.StaffLeavesAppliedResponse;
import app.managementapp.college.com.collegemanagement.api.Staff.StaffList.StaffListResponse;
import app.managementapp.college.com.collegemanagement.api.Staff.StaffMemoEntry.StaffMemoEntryResponse;
import app.managementapp.college.com.collegemanagement.api.Staff.StaffMovementRegister.StaffMovementRegisterResponse;
import app.managementapp.college.com.collegemanagement.api.Staff.StaffSeminars.StaffSeminarsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sri Harrsha on 05-Jul-16.
 */
public interface CollegeManagementApiService {

    @GET("ManagementService.svc/GetGeneralFeedbackAndComplaints")
    Call<FeedbackListResponse> getFeedbackList(@Header("Token") String token, @Query("Type") Integer type);

    @POST("ManagementService.svc/UpdateGeneralFeedbackAndComplaints")
    Call<FeedbackListResponse> giveReplyForFeedback(@Header("Token") String token, @Body app.managementapp.college.com.collegemanagement.api.FeedbackReply.FeedbackReplyRequest requestObject);

    @POST("authenticationService.svc/AuthenticateRequestForFirstLogin")
    Call<app.managementapp.college.com.collegemanagement.api.Authentication.FirstAuth.FirstLoginResponse> doFirstLogin(@Query("username") String username, @Query("Password") String password);

    @POST("AuthenticationService.svc/AuthenticateRequest")
    Call<app.managementapp.college.com.collegemanagement.api.Authentication.RegularAuth.RegularLoginResponse> doRegularLogin(@Query("username") String username, @Query("Password") String password);

    @GET("ManagementService.svc/GetAcademicCalendar")
    Call<AcademicCalenderResponse> getAcademicCalendar(@Header("Token") String token);

    @GET("ManagementService.svc/GetAdmissionDetails")
    Call<AdmissionDetailsResponse> getAdmissionDetails(@Header("Token") String token, @Query("StudentID") Integer studentid);

    @GET("ManagementService.svc/GetAttendanceDetails")
    Call<AttendanceDetailsResponse> getAttendanceDetails(@Header("Token") String token, @Query("StudentID") Integer studentid);

    @GET("ManagementService.svc/GetBranchOrCycle")
    Call<BranchOrCycleResponse> getBranchOrCycle(@Header("Token") String token, @Query("CourseID") Integer courseid, @Query("sem") Integer sem);

    @GET("ManagementService.svc/GetCollegeProfile")
    Call<CollegeProfileResponse> getCollegeProfile(@Header("Token") String token, @Query("ID") Integer id);

    @GET("ManagementService.svc/GetCourseFeedback")
    Call<CourseFeedbackResponse> getCourseFeedback(@Header("Token") String token, @Query("StaffID") Integer staffid);

    @GET("ManagementService.svc/GetCourseList")
    Call<CourseListResponse> getCourseList(@Header("Token") String token);

    @GET("ManagementService.svc/GetExamMark")
    Call<ExamMarkResponse> getExamMarks(@Header("Token") String token, @Query("StudentID") Integer studentid);

    @GET("ManagementService.svc/GetFeePaymentDetails")
    Call<FeePaymentDetailsResponse> getFeePaymentDetails(@Header("Token") String token, @Query("StudentID") Integer studentid);

    @GET("ManagementService.svc/GetUniversityProfile")
    Call<UniversityProfileResponse> getUniversityProfile(@Header("Token") String token, @Query("ID") Integer id);

    @GET("ManagementService.svc/GetQualificationDetails")
    Call<QualificationDetailsResponse> getQualificationDetails(@Header("Token") String token, @Query("StudentID") Integer studentid);

    @GET("ManagementService.svc/GetSemester")
    Call<SemesterResponse> getSemester(@Header("Token") String token, @Query("CourseID") Integer courseid);

    @GET("ManagementService.svc/GetDepartment")
    Call<DepartmentResponse> getDepartment(@Header("Token") String token);

    @GET("ManagementService.svc/GetStaffAttendance")
    Call<StaffAttendanceResponse> getStaffAttendance(@Header("Token") String token, @Query("StaffID") String staffid);

    @GET("ManagementService.svc/GetStaffFeedback")
    Call<StaffFeedbackResponse> getStaffFeedback(@Header("Token") String token, @Query("Staffid") String staffid);

    @GET("ManagementService.svc/GetStaffLeavesApplied")
    Call<StaffLeavesAppliedResponse> getStaffLeavesApplied(@Header("Token") String token, @Query("Staffid") String staffid);

    @GET("ManagementService.svc/GetStaffList")
    Call<StaffListResponse> getStaffList(@Header("Token") String token, @Query("StaffName") String staffname, @Query("StaffCode") String staffcode);

    @GET("ManagementService.svc/GetStaffMemoEntry")
    Call<StaffMemoEntryResponse> getStaffMemoEntry(@Header("Token") String token, @Query("StaffID") String staffid);

    @GET("ManagementService.svc/GetStaffMovementRegister")
    Call<StaffMovementRegisterResponse> getStaffMovementRegister(@Header("Token") String token, @Query("StaffID") String staffid, @Query("FromDate") String fromdate, @Query("ToDate") String todate);

    @GET("ManagementService.svc/GetStaffSeminar")
    Call<StaffSeminarsResponse> getStaffSeminar(@Header("Token") String token, @Query("StaffID") String staffid);

    @GET("")
}
