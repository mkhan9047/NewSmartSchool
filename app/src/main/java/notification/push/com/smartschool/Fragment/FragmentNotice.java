package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import notification.push.com.smartschool.Adapter.NoticeRecycleAdapter;
import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Notice;
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
public class FragmentNotice extends Fragment {

    RecyclerView recyclerView;
    NoticeRecycleAdapter adapter;
    List<Notice.Items> items;
    public FragmentNotice() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_notice, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Stroage stroage = new Stroage(getActivity());
        stroage.SaveNoticeCount(0);
        stroage.SaveNoticeSate(true);

        if(getView()!=null){
            recyclerView = getView().findViewById(R.id.notice_recyler);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        items = new ArrayList<>();
if(Helper.isInternetAvaiable(getActivity())){
    onDataLoad();
}else{
    Toast.makeText(getActivity(), "No Internet!", Toast.LENGTH_SHORT).show();
}

    }

    private void onDataLoad(){
        final ProgressDialog dailog = new ProgressDialog(getActivity());
        dailog.setMessage("Getting Notices....");
        dailog.show();
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Notice> getNotice = retrofitInterface.getNotice();
        getNotice.enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                Notice notice = response.body();
                if(notice != null){
                    items = notice.getNotice();
                    @SuppressLint("SimpleDateFormat")
                    final SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                    Collections.sort(items, new Comparator<Notice.Items>() {
                        @Override
                        public int compare(Notice.Items items, Notice.Items t1) {
                            try {
                                return df.parse(t1.getCreated_date()).compareTo(df.parse(items.getCreated_date()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                    adapter = new NoticeRecycleAdapter(items, getFragmentManager());
                    recyclerView.setAdapter(adapter);
                    if(dailog.isShowing()){
                        dailog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                if(t instanceof SocketTimeoutException){
                    Toast.makeText(getActivity(), "Connection Timeout!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
