package notification.push.com.smartschool.LocalStroage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mujahid on 5/19/2018.
 */

public class Stroage {

    private static final boolean LOG_IN_STATE = false;
    private static final String CURRENT_USER = "N/A";
    private static final String CURRENT_USER_REG = "N/A";
    private static final int NOTICE_COUNT = 0;

    private Context context;

    public Stroage(Context context) {
        this.context = context;
    }


    private SharedPreferences.Editor getPreferencesEditor() {
        return getsharedPreferences().edit();
    }
    private SharedPreferences getsharedPreferences() {

        return context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
    }

    public void SaveLogInSate(boolean p) {
        getPreferencesEditor().putBoolean("logged_in", p).commit();

    }
    public boolean getLogInState() {

        return getsharedPreferences().getBoolean("logged_in", LOG_IN_STATE);
    }

    public void SaveCurrentUSer(String name){
        getPreferencesEditor().putString("user",name).commit();
    }
    public String GetCurentUser(){
        return getsharedPreferences().getString("user",CURRENT_USER);
    }

    public void SaveUserRegNo(String no){
        getPreferencesEditor().putString("number",no).commit();
    }

    public String GetCurentUserReg(){
        return getsharedPreferences().getString("number",CURRENT_USER_REG);
    }


    public void SaveNoticeCount(int c){
        getPreferencesEditor().putInt("count",c).commit();
    }
    public int GetNoticeCount(){
        return getsharedPreferences().getInt("user",NOTICE_COUNT);
    }

}
