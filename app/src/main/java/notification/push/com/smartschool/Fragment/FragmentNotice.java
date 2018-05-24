package notification.push.com.smartschool.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Adapter.NoticeRecycleAdapter;
import notification.push.com.smartschool.Models.Notice;
import notification.push.com.smartschool.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNotice extends Fragment {

    RecyclerView recyclerView;
    NoticeRecycleAdapter adapter;

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
        if(getView()!=null){
            recyclerView = getView().findViewById(R.id.notice_recyler);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        List<Notice> notices = new ArrayList<>();
        notices.add(new Notice(1,"10-05-2018","School Function","In 05 may there is function is the school every parent's are being called for that","8-3-2018"));
        notices.add(new Notice(1,"09-05-2018","School Holiday","In 05 may there is function is the school every parent's are being called for that","8-3-2018"));
        notices.add(new Notice(1,"08-05-2018","School Sports","In 05 may there is function is the school every parent's are being called for that","8-3-2018"));
        notices.add(new Notice(1,"07-05-2018","School Exam","In 05 may there is function is the school every parent's are being called for that","8-3-2018"));

        adapter = new NoticeRecycleAdapter(notices);
        recyclerView.setAdapter(adapter);
    }
}
