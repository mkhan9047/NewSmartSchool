package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import notification.push.com.smartschool.Models.Holidays;
import notification.push.com.smartschool.Networking.RetrofitClient;
import notification.push.com.smartschool.Networking.RetrofitInterface;
import notification.push.com.smartschool.R;
import notification.push.com.smartschool.Utility.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HolidayCalender extends Fragment {

    HashMap<Date, Drawable> background;
    ColorDrawable blue;
    CaldroidFragment caldroidFragment;
    Holidays holidays;
    public HolidayCalender() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_holiday_calender, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        getHolidDays();

        background = new HashMap<>();
       // blue = new ColorDrawable(R);
        android.support.v4.app.FragmentTransaction t = getChildFragmentManager().beginTransaction();
        t.replace(R.id.holiday_frame, caldroidFragment);
        t.commit();


        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

                for(int i = 0; i < holidays.getHolidays().size(); i++){
                    try {
                        if(formater.parse(holidays.getHolidays().get(i).getFrom_date()).equals(formater.parse(holidays.getHolidays().get(i).getTo_date()))){
                            try {
                                if(formater.parse(holidays.getHolidays().get(i).getFrom_date()).equals(date)){
                                    dialog(holidays.getHolidays().get(i).getHoliday_name(),holidays.getHolidays().get(i).getFrom_date(),holidays.getHolidays().get(i).getHoliday_name());
                                }
                            } catch (ParseException e) {

                                e.printStackTrace();
                            }
                        }else{
                            if(date.after(formater.parse(holidays.getHolidays().get(i).getFrom_date())) && date.before(formater.parse(holidays.getHolidays().get(i).getTo_date()))){
                               dialog(holidays.getHolidays().get(i).getHoliday_name(),holidays.getHolidays().get(i).getFrom_date(),holidays.getHolidays().get(i).getTo_date(),holidays.getHolidays().get(i).getHoliday_name());
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        caldroidFragment.refreshView();

    }

    private void getHolidDays() {
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Holidays> holidaysCall = retrofitInterface.getHolidays();
        holidaysCall.enqueue(new Callback<Holidays>() {
            @Override
            public void onResponse(Call<Holidays> call, Response<Holidays> response) {

                holidays = response.body();

                if (holidays != null) {

                    List<Date> dates = Helper.getHoliDays(holidays.getHolidays());

                    for (Date d : dates) {

                        background.put(d, getResources().getDrawable(R.drawable.date_marker));

                    }

                    caldroidFragment.setBackgroundDrawableForDates(background);
                    caldroidFragment.refreshView();

                }
            }

            @Override
            public void onFailure(Call<Holidays> call, Throwable t) {
                Log.d("HolidayError", t.getMessage());
            }
        });

    }
    @SuppressLint("DefaultLocale")
    void dialog(String Title, String date, String oca) {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(getActivity());
        //alt_bld.setIcon(R.drawable.icon);
        alt_bld.setTitle(Title);
        alt_bld.setMessage(String.format("All term of the School will be off on %s for %s\n\n Total Off Days: %d",date, oca,1));

        alt_bld.setNeutralButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            }
        });
        AlertDialog alert = alt_bld.create();
        alert.show();

    }

    @SuppressLint("DefaultLocale")
    void dialog(String Title, String fromDate,String toDate,String oca) {
        Date from = null;
        Date to = null;
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(getActivity());
        //alt_bld.setIcon(R.drawable.icon);
        alt_bld.setTitle(Title);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
             from = formater.parse(fromDate);
             to = formater.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        alt_bld.setMessage(String.format("All term of the School will be off from %s to %s for %s. \n\n Total Off Days: %d",fromDate,toDate,oca,Helper.getDifferenceDays(from,to)));


       alt_bld.setNeutralButton("Done", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
           }
       });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    }


