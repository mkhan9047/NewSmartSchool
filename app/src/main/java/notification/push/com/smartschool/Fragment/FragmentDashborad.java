package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import notification.push.com.smartschool.Activity.Dashboard;
import notification.push.com.smartschool.Adapter.NoticeRecycleAdapter;
import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Homework;
import notification.push.com.smartschool.Models.Notice;
import notification.push.com.smartschool.Networking.RetrofitClient;
import notification.push.com.smartschool.Networking.RetrofitInterface;
import notification.push.com.smartschool.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDashborad extends Fragment {

    TextView indicator, home_indicator;
    Stroage stroage;
    CardView finance, homework;
    public FragmentDashborad() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashbaord, container, false);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        stroage = new Stroage(getActivity());
       // GetNoticeCount();
       // getHomeworkCount();
        View view = getView();
        if(view!=null){
           // indicator = view.findViewById(R.id.notice_indicator);
           // home_indicator = view.findViewById(R.id.homework_indicator);
            finance = view.findViewById(R.id.notice);
            homework =view.findViewById(R.id.homework);
            homework.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new HomeworkFragment();
                    ((Dashboard)getActivity()).FragmentTransction(fragment,"Homework",R.id.nav_homework);
                }
            });
            finance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new FragmentNotice();
                    ((Dashboard)getActivity()).FragmentTransction(fragment,"Notification",R.id.nav_notification);
                }
            });


        }
    }

    private void GetNoticeCount(){
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Notice> getNotice = retrofitInterface.getNotice();
        getNotice.enqueue(new Callback<Notice>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                Notice notice = response.body();
                if(notice != null){
                    if(!stroage.getNoticeState()){



                        if(stroage.GetNoticeHolder() > 0){
                            if(notice.getNotice().size() > stroage.GetNoticeHolder()){
                                stroage.SaveNoticeCount(notice.getNotice().size());
                                indicator.setText(String.format("%d",(notice.getNotice().size() - stroage.GetNoticeHolder())));
                                stroage.SaveNoticeHolder(notice.getNotice().size());
                            }
                        }else {
                            stroage.SaveNoticeCount(notice.getNotice().size());
                            stroage.SaveNoticeHolder(notice.getNotice().size());
                            indicator.setText(String.format("%d",stroage.GetNoticeCount()));
                        }

                    }else{

                        if(stroage.GetNoticeHolder() > 0){
                            if(notice.getNotice().size() > stroage.GetNoticeHolder()){
                                stroage.SaveNoticeCount(notice.getNotice().size());
                                indicator.setText(String.format("%d",(notice.getNotice().size() - stroage.GetNoticeHolder())));
                                stroage.SaveNoticeHolder(notice.getNotice().size());
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                Log.d("noticeerror",t.getMessage());
            }
        });
    }

    private void getHomeworkCount(){

        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
       Call<Homework> homeworkCall =  retrofitInterface.getHomework(stroage.GetCurentUserReg());
       homeworkCall.enqueue(new Callback<Homework>() {
           @SuppressLint("DefaultLocale")
           @Override
           public void onResponse(Call<Homework> call, Response<Homework> response) {
               Homework homework = response.body();
               if(homework!=null){
                   if(!stroage.getHomeworkState()){

                       stroage.SaveHomeworkCount(homework.getHomeworks().size());
                       stroage.SaveHomeworkHolder(homework.getHomeworks().size());
                       home_indicator.setText(String.format("%d",stroage.GetHomeworkCount()));

                       if(stroage.GetHomeworkHolder() > 0){
                           if(homework.getHomeworks().size() > stroage.GetHomeworkHolder()){
                               stroage.SaveHomeworkCount(homework.getHomeworks().size());
                               home_indicator.setText(String.format("%d",(homework.getHomeworks().size() - stroage.GetHomeworkHolder())));
                               stroage.SaveHomeworkHolder(homework.getHomeworks().size());
                           }
                       }

                   }else{

                       if(stroage.GetHomeworkHolder() > 0){
                           if(homework.getHomeworks().size() > stroage.GetHomeworkHolder()){
                               stroage.SaveHomeworkCount(homework.getHomeworks().size());
                               home_indicator.setText(String.format("%d",(homework.getHomeworks().size() - stroage.GetHomeworkHolder())));
                               stroage.SaveHomeworkHolder(homework.getHomeworks().size());
                           }
                       }
                   }
               }
           }

           @Override
           public void onFailure(Call<Homework> call, Throwable t) {

           }
       });

    }
}
