package app.managementapp.college.com.collegemanagement.api;


import app.managementapp.college.com.collegemanagement.api.AcademicCalender.AcademicCalenderResponse;
import app.managementapp.college.com.collegemanagement.api.AdmissionDetails.AdmissionDetailsResponse;
import app.managementapp.college.com.collegemanagement.api.AttendanceDetails.AttendanceDetailsResponse;
import app.managementapp.college.com.collegemanagement.api.BranchOrCycle.BranchOrCycleResponse;
import app.managementapp.college.com.collegemanagement.api.CollegeProfile.CollegeProfileResponse;
import app.managementapp.college.com.collegemanagement.api.FeedbackList.FeedbackListResponse;
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
    Call<AdmissionDetailsResponse> getAdmissionDetails(@Header("Token") String token);

    @GET("ManagementService.svc/GetAttendanceDetails")
    Call<AttendanceDetailsResponse> getAttendanceDetails(@Header("Token") String token);

    @GET("ManagementService.svc/GetBranchOrCycle")
    Call<BranchOrCycleResponse> getBranchOrCycle(@Header("Token") String token, @Query("CourseID") Integer courseid, @Query("sem") Integer sem);

    @GET("ManagementService.svc/GetCollegeProfile")
    Call<CollegeProfileResponse> getCollegeProfile(@Header("Token") String token, @Query("ID") Integer id);

    @GET("ManagementService.svc/GetCousreFeedback")

}
