package notification.push.com.smartschool.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

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
public class FeesFragment extends Fragment {



    TextView name, year;
    Fragment fragment;
    public FeesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fees, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name = getView().findViewById(R.id.fee_name);
        year = getView().findViewById(R.id.fee_year);
        Stroage stroage = new Stroage(getActivity());
        name.setText(String.format("Monthly Paid Fee of %s",stroage.GetCurentUser()));
        getFees();

        fragment = new Monthly_fee();
        FragmentTransition();
        MaterialSpinner MTspinner = (MaterialSpinner) getView().findViewById(R.id.spinner);
        MTspinner.setItems("Monthly Fee", "Other Fee", "Bus Fee");
        MTspinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Toast.makeText(getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                switch (position){
                    case 0:
                        fragment = new Monthly_fee();
                        FragmentTransition();
                        break;
                    case 1:
                        fragment = new OtherFee();
                        FragmentTransition();
                        break;
                    case 2:
                        fragment = new BusFee();
                        FragmentTransition();
                        break;
                }
            }
        });
    }


    private void getFees(){

        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Fee> feeCall = retrofitInterface.getStudentFees(stroage.GetCurentUserReg());
        feeCall.enqueue(new Callback<Fee>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(Call<Fee> call, Response<Fee> response) {
                Fee fee = response.body();
                if(fee != null){
                     year.setText(String.format("Year: %d",fee.getYear()));
                }

            }

            @Override
            public void onFailure(Call<Fee> call, Throwable t) {
                Log.d("feeError",t.getMessage());
            }
        });

    }

    private void FragmentTransition(){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fee_frame,fragment);
        fragmentTransaction.commit();
    }

}
