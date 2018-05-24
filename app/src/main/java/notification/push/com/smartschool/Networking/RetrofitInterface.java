package notification.push.com.smartschool.Networking;

import java.util.List;

import notification.push.com.smartschool.Models.LogIn;
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
}
