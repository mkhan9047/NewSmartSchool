package notification.push.com.smartschool.Utility;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.util.Log;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import notification.push.com.smartschool.Activity.Dashboard;
import notification.push.com.smartschool.Models.Attendence;
import notification.push.com.smartschool.Models.Holidays;

/**
 * Created by Mujahid on 5/22/2018.
 */

public class Helper {
        private static String base_download_url = "http://doon.yawun.com/teacher@apanel123/homework/";

    public static String getAbsolute(String s){
        String newline = s.replace("\n","");
        String touch = newline.replace("\t","");
        String r = touch.replace("\r","");
        String space =  r.replace("&nbsp;"," ");
        String last =  space.replace("&#39;"," ");
        return last.replace("&#039;", "");

    }

    public static void downlaodFile(Context context, String filename){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Helper.base_download_url + filename));
        context.startActivity(browserIntent);
    }

    public static List<Date> getHoliDays(List<Holidays.Days> d){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> finalDates = new ArrayList<>();
        for(int i = 0; i < d.size(); i++){
            try {
                if(formater.parse(d.get(i).getFrom_date()).equals(formater.parse(d.get(i).getTo_date()))){
                    finalDates.add(formater.parse(d.get(i).getFrom_date()));

                }else{
                    Calendar start = Calendar.getInstance();
                    Calendar end = Calendar.getInstance();
                    start.setTime(formater.parse(d.get(i).getFrom_date()));
                    end.setTime(formater.parse(d.get(i).getTo_date()));
                    finalDates.add(formater.parse(d.get(i).getFrom_date()));
                    while (start.getTime().before(end.getTime())){
                        start.add(Calendar.DATE,1);
                        finalDates.add(start.getTime());
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return finalDates;
    }

    public static List<Date> getSchoolDays(List<Attendence.AttendenceData> data){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> finalDates = new ArrayList<>();

        for(int i = 0; i < data.size(); i++){
            try {
               finalDates.add(formater.parse(data.get(i).getAttendance_date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return finalDates;
    }

    public static int getDifferenceDays(Date d1, Date d2) {
        int daysdiff = 0;
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }

    public static boolean isInternetAvaiable(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
