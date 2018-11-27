package notification.push.com.smartschool.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import notification.push.com.smartschool.Adapter.BusFeeAdapter;
import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Bus;
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
public class BusFee extends Fragment {

    RecyclerView recyclerView;
    BusFeeAdapter adapter;
    List<Bus.Transport> busList;


    public BusFee() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_fee, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if(view!= null){
            recyclerView = view.findViewById(R.id.bus_recycle);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
        }

        if(Helper.isInternetAvaiable(getActivity())){
            getBusFee();
        }else{
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void getBusFee(){
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Bus> busCall = retrofitInterface.getBusFee(stroage.GetCurentUserReg());
        busCall.enqueue(new Callback<Bus>() {
            @Override
            public void onResponse(Call<Bus> call, Response<Bus> response) {
            Bus bus = response.body();
        if(bus!=null){
            busList = bus.getBus_fee();
            adapter = new BusFeeAdapter(busList);

            recyclerView.setAdapter(adapter);
        }
            }

            @Override
            public void onFailure(Call<Bus> call, Throwable t) {

            }
        });
    }

}
