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
    private static final boolean NOTICE_STATE = false;
    private static final int NOTICE_HOLDER = 0;

    private static final int HOMEWORK_COUNT = 0;
    private static final boolean HOMEWORK_STATE = false;
    private  static final int HOMEWORK_HOLDER = 0;


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


    public void SaveNoticeCount(Integer c){
        getPreferencesEditor().putInt("count",c).commit();
    }
    public Integer GetNoticeCount(){
        return getsharedPreferences().getInt("count",NOTICE_COUNT);
    }

    public void SaveNoticeSate(boolean p) {
        getPreferencesEditor().putBoolean("show", p).commit();
    }

    public boolean getNoticeState() {

        return getsharedPreferences().getBoolean("show", NOTICE_STATE);
    }
    public void SaveNoticeHolder(Integer c){
        getPreferencesEditor().putInt("c",c).commit();
    }
    public Integer GetNoticeHolder(){
        return getsharedPreferences().getInt("c",NOTICE_HOLDER);
    }



    //homework part
    public void SaveHomeworkCount(Integer c){
        getPreferencesEditor().putInt("home",c).commit();
    }
    public Integer GetHomeworkCount(){
        return getsharedPreferences().getInt("home",HOMEWORK_COUNT);
    }

    public void SaveHomeworkSate(boolean p) {
        getPreferencesEditor().putBoolean("home_show", p).commit();
    }

    public boolean getHomeworkState() {

        return getsharedPreferences().getBoolean("home_show", HOMEWORK_STATE);
    }

    public void SaveHomeworkHolder(Integer c){
        getPreferencesEditor().putInt("holder",c).commit();

    }
    public Integer GetHomeworkHolder() {
        return getsharedPreferences().getInt("holder",HOMEWORK_HOLDER);
    }


}
