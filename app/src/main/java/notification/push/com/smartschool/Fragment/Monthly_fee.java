package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
    CardView janCard, fabCard, marCard, aprCard, mayCard, junCard, julCard, augCard, sepCard, octCard, novCard, decCard;

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

            janCard = view.findViewById(R.id.jan_card);
            fabCard = view.findViewById(R.id.feb_card);
            marCard = view.findViewById(R.id.mar_card);
            aprCard = view.findViewById(R.id.apr_card);
            mayCard = view.findViewById(R.id.may_card);
            junCard = view.findViewById(R.id.jun_card);
            julCard = view.findViewById(R.id.jul_card);
            augCard = view.findViewById(R.id.aug_card);
            sepCard = view.findViewById(R.id.sep_card);
            octCard = view.findViewById(R.id.oct_card);
            novCard = view.findViewById(R.id.nov_card);
            decCard = view.findViewById(R.id.dec_card);
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

                   if(fee.getJan() == 1){

                   jan.setText("PAID");
                       janCard.setVisibility(View.VISIBLE);
                   }
                    if(fee.getFeb() == 1){
                        fab.setText("PAID");
                        fabCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getMar() == 1){
                        mar.setText("PAID");
                        marCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getArp() == 1){
                        apr.setText("PAID");
                        aprCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getMay() == 1){
                        may.setText("PAID");
                        mayCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getJun() == 1){
                        jun.setText("PAID");
                        junCard.setVisibility(View.VISIBLE);
                    }
                    if(fee.getJul() == 1){
                        jul.setText("PAID");
                        julCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getAug() == 1){
                        aug.setText("PAID");
                        augCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getSep() == 1){
                        sep.setText("PAID");
                        sepCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getOct() == 1){
                        oct.setText("PAID");
                        octCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getNov() == 1){
                        nov.setText("PAID");
                        novCard.setVisibility(View.VISIBLE);
                    }

                    if(fee.getDec() == 1){
                        dec.setText("PAID");
                        decCard.setVisibility(View.VISIBLE);
                    }



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
