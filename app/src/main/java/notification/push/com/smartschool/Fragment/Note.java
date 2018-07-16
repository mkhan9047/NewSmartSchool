package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import java.util.List;

import notification.push.com.smartschool.Adapter.NoteRecyleAdapter;
import notification.push.com.smartschool.Adapter.NoticeRecycleAdapter;
import notification.push.com.smartschool.Dialog.NoteDailogFragment;
import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Notes;
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
public class Note extends Fragment {

    RecyclerView recyclerView;
    NoteRecyleAdapter adapter;
 List<Notes.NoteItems> items;

    public Note() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();

        if(view != null){
            recyclerView = view.findViewById(R.id.note_recyler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
        }
        items = new ArrayList<>();
        if(Helper.isInternetAvaiable(getActivity())){
          onDataLoad();
        }else{
            Toast.makeText(getActivity(), "No Internet!", Toast.LENGTH_SHORT).show();
        }

    }

    private void onDataLoad(){
        final ProgressDialog dailog = new ProgressDialog(getActivity());
        dailog.setMessage("Getting Notes....");
        dailog.show();
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
       Call<Notes> notesCall=  retrofitInterface.getNotes(stroage.GetCurentUserReg());
       notesCall.enqueue(new Callback<Notes>() {
           @Override
           public void onResponse(Call<Notes> call, Response<Notes> response) {
               Notes notes = response.body();
               if(notes!=null){
                   items = notes.getNotes();
                   @SuppressLint("SimpleDateFormat")
                   final SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                   Collections.sort(items, new Comparator<Notes.NoteItems>() {
                       @Override
                       public int compare(Notes.NoteItems items, Notes.NoteItems t1) {
                           try {
                               return df.parse(t1.getCreated_date()).compareTo(df.parse(items.getCreated_date()));
                           } catch (ParseException e) {
                               e.printStackTrace();
                           }
                           return 0;
                       }
                   });
                   Activity activity = getActivity();
                   if(activity != null){
                       adapter = new NoteRecyleAdapter(items, activity.getApplicationContext(),getFragmentManager());
                       recyclerView.setAdapter(adapter);
                   }

                   if(dailog.isShowing()){
                       dailog.dismiss();
                   }
               }
           }

           @Override
           public void onFailure(Call<Notes> call, Throwable t) {
               Log.d("error",t.getMessage());
               if(t instanceof SocketTimeoutException){
                   Toast.makeText(getActivity(), "Connection Timeout!", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }




}
