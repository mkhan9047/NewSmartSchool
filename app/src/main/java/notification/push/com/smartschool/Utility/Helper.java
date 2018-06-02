package notification.push.com.smartschool.Utility;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
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
import notification.push.com.smartschool.Models.Holidays;

/**
 * Created by Mujahid on 5/22/2018.
 */

public class Helper {
        private static String base_download_url = "http://doon.maarina.com/teacher@apanel123/homework/";
    public static String getAbsolute(String s){
        String newline = s.replace("\n","");
        String touch = newline.replace("\t","");
        String r = touch.replace("\r","");
        String space =  r.replace("&nbsp;"," ");
        return space.replace("&#39;"," ");
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

}
