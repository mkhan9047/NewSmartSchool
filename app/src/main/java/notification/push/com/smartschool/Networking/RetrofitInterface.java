package notification.push.com.smartschool.Networking;

import java.util.List;

import notification.push.com.smartschool.Models.Holidays;
import notification.push.com.smartschool.Models.Homework;
import notification.push.com.smartschool.Models.LogIn;
import notification.push.com.smartschool.Models.Notes;
import notification.push.com.smartschool.Models.Notice;
import retrofit2.Call;
import retrofit2.http.GET;
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

}
