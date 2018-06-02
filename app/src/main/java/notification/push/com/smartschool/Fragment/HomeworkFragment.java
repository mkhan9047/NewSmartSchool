package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import notification.push.com.smartschool.Adapter.HomeworkAdapter;
import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Homework;
import notification.push.com.smartschool.Models.Notes;
import notification.push.com.smartschool.Networking.RetrofitClient;
import notification.push.com.smartschool.Networking.RetrofitInterface;
import notification.push.com.smartschool.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeworkFragment extends Fragment {

    RecyclerView recyclerView;
    HomeworkAdapter adapter;
    List<Homework.Works> homework;

    public HomeworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homework, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        Stroage stroage = new Stroage(getActivity());
        stroage.SaveHomeworkCount(0);
        stroage.SaveHomeworkSate(true);

        if(view != null){
            recyclerView = view.findViewById(R.id.homework_recycle);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
        }
        homework = new ArrayList<>();
        onDataCall();
    }

    private void onDataCall() {
        final ProgressDialog dailog = new ProgressDialog(getActivity());
        dailog.setMessage("Getting Homeworks....");
        dailog.show();
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        final Call<Homework> homeworkCall = retrofitInterface.getHomework(stroage.GetCurentUserReg());
        homeworkCall.enqueue(new Callback<Homework>() {
            @Override
            public void onResponse(Call<Homework> call, Response<Homework> response) {
                Homework work = response.body();
                if(work != null){
                    homework = work.getHomeworks();
                    @SuppressLint("SimpleDateFormat")
                    final SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                    Collections.sort(homework, new Comparator<Homework.Works>() {
                        @Override
                        public int compare(Homework.Works items, Homework.Works t1) {
                            try {
                                return df.parse(t1.getCreated_date()).compareTo(df.parse(items.getCreated_date()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });

                    adapter = new HomeworkAdapter(homework, getFragmentManager(), getActivity());
                    recyclerView.setAdapter(adapter);
                    if(dailog.isShowing()){
                        dailog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<Homework> call, Throwable t) {

            }
        });
    }
}
