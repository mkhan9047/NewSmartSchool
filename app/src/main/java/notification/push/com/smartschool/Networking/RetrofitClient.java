package notification.push.com.smartschool.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mujahid on 5/22/2018.
 */

public class RetrofitClient {
    private static final String baseURL = "http://doon.maarina.com/";
    private static final String DOWNLOAD_BASE = "http://doon.maarina.com/teacher@apanel123/homework/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
