package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Attendence;
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
public class AttendenceFragment extends Fragment {


    HashMap<Date, Drawable> background;
    ColorDrawable blue;
    CaldroidFragment caldroidFragment;

    public AttendenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendence, container, false);
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

        if(Helper.isInternetAvaiable(getActivity())){
            getAttendence();
        }else{
            Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
        background = new HashMap<>();
        // blue = new ColorDrawable(R);
        android.support.v4.app.FragmentTransaction t = getChildFragmentManager().beginTransaction();
        t.replace(R.id.attendence_frame, caldroidFragment);
        t.commit();
    }

    private  void getAttendence(){
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Getting Attendance....");
        dialog.setCancelable(false);
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Attendence> getData = retrofitInterface.getAttendence(stroage.GetCurentUserReg());
        getData.enqueue(new Callback<Attendence>() {
            @Override
            public void onResponse(Call<Attendence> call, Response<Attendence> response) {

                Attendence attendence = response.body();

                if(attendence!=null){

                    List<Date> schoolDays = Helper.getSchoolDays(attendence.getAttendence());
                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    for(Date every : schoolDays){

                        for(int i = 0 ; i < attendence.getAttendence().size(); i++){
                            try {
                                if(every.equals(formater.parse(attendence.getAttendence().get(i).getAttendance_date()))){
                                    if(attendence.getAttendence().get(i).getHalf_day()== 0  && attendence.getAttendence().get(i).getLeave_status()==0 && attendence.getAttendence().get(i).getStatus()==1){
                                        if(isAdded()){
                                            background.put(every, getResources().getDrawable(R.drawable.attendent_maker));
                                        }

                                    }else if(attendence.getAttendence().get(i).getHalf_day()== 0  && attendence.getAttendence().get(i).getLeave_status()== 0 && attendence.getAttendence().get(i).getStatus()==0){
                                        if(isAdded()){
                                            background.put(every, getResources().getDrawable(R.drawable.not_attendent_maker));
                                        }

                                    }else if(attendence.getAttendence().get(i).getHalf_day()== 1){
                                        if(isAdded()){
                                            background.put(every, getResources().getDrawable(R.drawable.half_day_maker));
                                        }

                                    }else if(attendence.getAttendence().get(i).getLeave_status()== 1){
                                        if(isAdded()){
                                            background.put(every, getResources().getDrawable(R.drawable.leaved_maker));
                                        }

                                    }

                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    caldroidFragment.setBackgroundDrawableForDates(background);
                    caldroidFragment.refreshView();
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<Attendence> call, Throwable t) {
                Log.d("attendenceError",t.getMessage());
            }
        });

    }

}
