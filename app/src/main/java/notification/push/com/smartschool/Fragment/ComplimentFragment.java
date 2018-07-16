package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.PostComplaint;
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
public class ComplimentFragment extends Fragment {

    EditText subject, message;
    Button sendComplain;


    public ComplimentFragment() {
        // Required empty public constructor
    }

    private void InitView(){
        View view = getView();
        if(view != null){
            subject = view.findViewById(R.id.subject);
            message = view.findViewById(R.id.message);
            sendComplain = view.findViewById(R.id.btn_complaint);
        }
    }

    private void input_validation(){
        subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (subject.getText().toString().length() <= 0) {
                    subject.setError("Enter Message Subject!");
                }
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compliment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitView();
        input_validation();

        sendComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(message.getText().toString().length() > 0 && subject.getText().toString().length() > 0){
                    if(Helper.isInternetAvaiable(getActivity())){
                        PostData(subject.getText().toString(), message.getText().toString(), getDate(), getTime());
                    }else{
                        Toast.makeText(getActivity(), "No internet connection!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getActivity(), "Please input message!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private String getDate(){
        Date c = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(c);
    }

    private String getTime(){
        Date c = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("hh:mm");
        return df.format(c);
    }
    private void PostData(final String Msgsubject, String msg, String date, String time){
        final ProgressDialog dailog = new ProgressDialog(getActivity());
        dailog.setMessage("Sending Mesage....");
        dailog.show();
        final Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<PostComplaint> postComplaintCall = retrofitInterface.postComplaintData(stroage.GetCurentUserReg(),Msgsubject, msg,date,time);

        postComplaintCall.enqueue(new Callback<PostComplaint>() {
            @Override
            public void onResponse(Call<PostComplaint> call, Response<PostComplaint> response) {
                PostComplaint complaint = response.body();
                if(complaint != null){

                    if(complaint.getSuccess()==1){
                        subject.setText("");
                        message.setText("");
                        Toast.makeText(getActivity(), String.format("We have got your complaint %s. we will get back to you soon",stroage.GetCurentUser()), Toast.LENGTH_LONG).show();
                    }else if(complaint.getSuccess()==0){

                        Toast.makeText(getActivity(), "Complaint doesn't Send Successfully!", Toast.LENGTH_SHORT).show();
                    }

                }

              if(dailog.isShowing()){
                  dailog.dismiss();
              }


            }

            @Override
            public void onFailure(Call<PostComplaint> call, Throwable t) {

            }
        });
    }
}
