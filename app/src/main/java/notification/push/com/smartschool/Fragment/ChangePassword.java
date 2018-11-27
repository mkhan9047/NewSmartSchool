package notification.push.com.smartschool.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.PassChange;
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
public class ChangePassword extends Fragment {

    EditText oldPassword, newPassword, confirmPassword;
    AppCompatButton changeBTN;
    public ChangePassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Stroage stroage = new Stroage(getActivity());
        InitViews();
        changeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(oldPassword.getText().toString().equals(stroage.GetPassword())){
                    if(newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                        if(newPassword.getText().toString().length() > 6){
                            if(Helper.isInternetAvaiable(getActivity())){
                                ChangePassFun(newPassword.getText().toString());
                            }else{
                                Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Password can't be less than 6 character!", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(getActivity(), "Confirm password not match!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Wrong Current Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void InitViews(){
        View view = getView();
        if(view != null){
            oldPassword = view.findViewById(R.id.currentPass);
            newPassword = view.findViewById(R.id.newPass);
            confirmPassword = view.findViewById(R.id.confirmPass);
            changeBTN = view.findViewById(R.id.btn_changePass);
        }
    }

    private void ChangePassFun(String newPassword){
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Changing Password...");
        dialog.show();
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
       Call<PassChange> changeCall =  retrofitInterface.getChangedPass(stroage.GetCurentUserReg(), newPassword);
       changeCall.enqueue(new Callback<PassChange>() {
           @Override
           public void onResponse(Call<PassChange> call, Response<PassChange> response) {
               PassChange passChange = response.body();
               if(passChange != null){
                   if(passChange.getSuccess()==1){
                       Toast.makeText(getContext(), "Your Password has Changed!", Toast.LENGTH_SHORT).show();
                   }else if(passChange.getSuccess() == 0){
                       Toast.makeText(getContext(), "Your Password has not Changed!", Toast.LENGTH_SHORT).show();
                   }
               }
               if(dialog.isShowing()){
                   dialog.dismiss();
               }
           }

           @Override
           public void onFailure(Call<PassChange> call, Throwable t) {

           }
       });

    }
}
