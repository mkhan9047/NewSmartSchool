package notification.push.com.smartschool.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Adapter.OtherFeeAdapter;
import notification.push.com.smartschool.LocalStroage.Stroage;
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
public class OtherFee extends Fragment {


    RecyclerView recyclerView;
    OtherFeeAdapter adapter;
    List<notification.push.com.smartschool.Models.OtherFee.Fees> feesList;
    public OtherFee() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other_fee, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if(view != null){
            recyclerView = view.findViewById(R.id.other_recyle);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
            feesList = new ArrayList<>();
        }
        if(Helper.isInternetAvaiable(getActivity())){
            getOtherFee();
        }else{
            Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_LONG).show();
        }

    }

    private void getOtherFee(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Getting Fees Info...");
        progressDialog.show();
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<notification.push.com.smartschool.Models.OtherFee> otherFeeCall = retrofitInterface.getOtherFee(stroage.GetCurentUserReg());
        otherFeeCall.enqueue(new Callback<notification.push.com.smartschool.Models.OtherFee>() {
            @Override
            public void onResponse(Call<notification.push.com.smartschool.Models.OtherFee> call, Response<notification.push.com.smartschool.Models.OtherFee> response) {
                notification.push.com.smartschool.Models.OtherFee otherFee = response.body();
                if(otherFee!=null){
                    feesList = otherFee.getOther_fees();
                    adapter = new OtherFeeAdapter(feesList);
                    recyclerView.setAdapter(adapter);
                }

                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<notification.push.com.smartschool.Models.OtherFee> call, Throwable t) {

            }
        });

    }
}
