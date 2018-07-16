package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Fee;
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
public class Monthly_fee extends Fragment {
    TextView year, jan, fab, mar, apr, may, jun, jul, aug, sep, oct, nov, name, dec;

    public Monthly_fee() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitView();
        if (Helper.isInternetAvaiable(getActivity())) {
            getFees();
        } else {
            Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void InitView() {
        View view = getView();
        if (view != null) {
            jan = view.findViewById(R.id.jan_fee);
            fab = view.findViewById(R.id.feb_fee);
            mar = view.findViewById(R.id.mar_fee);
            apr = view.findViewById(R.id.apr_fee);
            may = view.findViewById(R.id.may_fee);
            jun = view.findViewById(R.id.jun_fee);
            jul = view.findViewById(R.id.jul_fee);
            aug = view.findViewById(R.id.aug_fee);
            sep = view.findViewById(R.id.sep_fee);
            oct = view.findViewById(R.id.oct_fee);
            nov = view.findViewById(R.id.nov_fee);
            dec = view.findViewById(R.id.dec_fee);

        }
    }



    private void getFees(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Getting Data....");
        progressDialog.show();
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Fee> feeCall = retrofitInterface.getStudentFees(stroage.GetCurentUserReg());
        feeCall.enqueue(new Callback<Fee>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(Call<Fee> call, Response<Fee> response) {
                Fee fee = response.body();
                if(fee != null){
                   // year.setText(String.format("Year: %d",fee.getYear()));
                    jan.setText(String.format("RS %d", fee.getJan()));
                    fab.setText(String.format("RS %d", fee.getFeb()));
                    mar.setText(String.format("RS %d", fee.getMar()));
                    apr.setText(String.format("RS %d", fee.getArp()));
                    may.setText(String.format("RS %d", fee.getMay()));
                    jun.setText(String.format("RS %d", fee.getJun()));
                    jul.setText(String.format("RS %d", fee.getJul()));
                    aug.setText(String.format("RS %d", fee.getAug()));
                    sep.setText(String.format("RS %d", fee.getSep()));
                    oct.setText(String.format("RS %d", fee.getOct()));
                    nov.setText(String.format("RS %d", fee.getNov()));
                    dec.setText(String.format("RS %d", fee.getDec()));

                }
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Fee> call, Throwable t) {
                Log.d("feeError",t.getMessage());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly_fee, container, false);
    }

}
