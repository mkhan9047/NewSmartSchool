package notification.push.com.smartschool.Networking;

import android.content.Intent;
import android.util.Log;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mujahid on 5/22/2018.
 */

public class RetrofitClient {
    private static final String baseURL = "http://doon.yawun.com/";
    private static final String DOWNLOAD_BASE = "http://doon.yawun.com/teacher@apanel123/homework/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        OkHttpClient client = new OkHttpClient.Builder().build();
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseURL).
                    client(client).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

}
