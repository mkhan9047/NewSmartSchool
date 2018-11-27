package notification.push.com.smartschool.Networking;

import java.util.List;

import notification.push.com.smartschool.Models.Attendence;
import notification.push.com.smartschool.Models.Bus;
import notification.push.com.smartschool.Models.Exam;
import notification.push.com.smartschool.Models.Fee;
import notification.push.com.smartschool.Models.Holidays;
import notification.push.com.smartschool.Models.Homework;
import notification.push.com.smartschool.Models.LogIn;
import notification.push.com.smartschool.Models.Marks;
import notification.push.com.smartschool.Models.Notes;
import notification.push.com.smartschool.Models.Notice;
import notification.push.com.smartschool.Models.OtherFee;
import notification.push.com.smartschool.Models.PassChange;
import notification.push.com.smartschool.Models.PostComplaint;
import notification.push.com.smartschool.Models.Student;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mujahid on 5/22/2018.
 */

public interface RetrofitInterface {
    @GET("api/student_login.php")
    Call<LogIn> getRepos(@Query("reg_no") String reg_no, @Query("pass") String pass);

    @GET("api/notice.php")
    Call<Notice> getNotice();

    @GET("api/note.php")
    Call<Notes> getNotes(@Query("reg_no") String reg_no);

    @GET("api/homework.php")
    Call<Homework> getHomework(@Query("reg_no") String reg);

    @GET("api/holidays.php")
    Call<Holidays> getHolidays();

    @GET("api/attendence.php")
    Call<Attendence> getAttendence(@Query("reg_no") String reg);

    @GET("api/student_fee.php")
    Call<Fee> getStudentFees(@Query("reg_no") String reg);


    @GET("api/complaint.php")
    Call<PostComplaint> postComplaintData(@Query("reg_no") String reg_no,
                                          @Query("message_subject") String messageSubject,
                                          @Query("message_body") String messageBody,
                                          @Query("complaint_date") String date,
                                          @Query("complaint_time") String time
                                          );

    @GET("api/other_fee.php")
    Call<List<String>> getOtherFee(@Query("reg_no") String reg);

    @GET("api/GetExamNames.php")
    Call<Exam> getExamNames(@Query("reg_no") String reg);

    @GET("api/getResult.php")
    Call<Marks> getMarks(@Query("reg_no") String reg_no, @Query("exam_type") String exam_type);

    @GET("api/profile.php")
    Call<Student> getStudentInfo(@Query("reg_no") String reg);

    @GET("api/bus_fee.php")
    Call<Bus> getBusFee(@Query("reg_no") String reg);

    @GET("api/change_password.php")
    Call<PassChange> getChangedPass(@Query("reg_no") String reg_no, @Query("pass") String pass);
}
