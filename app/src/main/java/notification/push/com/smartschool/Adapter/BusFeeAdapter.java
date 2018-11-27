package notification.push.com.smartschool.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import notification.push.com.smartschool.Models.Bus;
import notification.push.com.smartschool.R;

/**
 * Created by Mujahid on 8/2/2018.
 */

public class BusFeeAdapter extends RecyclerView.Adapter<BusFeeAdapter.BusHolder> {

    private List<Bus.Transport> busList;

    public BusFeeAdapter(List<Bus.Transport> busList) {
        this.busList = busList;
    }

    @NonNull
    @Override
    public BusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_fee_custom_layout,parent, false);
        return new BusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusHolder holder, int position) {

        switch (busList.get(position).getMonth()){
            case 1:
                holder.month.setText("January");
                break;
            case 2:
                holder.month.setText("February");
                break;
            case 3:
                holder.month.setText("March");
                break;
            case 4:
                holder.month.setText("April");
                break;
            case 5:
                holder.month.setText("May");
                break;
            case 6:
                holder.month.setText("June");
                break;
            case 7:
                holder.month.setText("July");
                break;
            case 8:
                holder.month.setText("August");
                break;
            case 9:
                holder.month.setText("September");
                break;
            case 10:
                holder.month.setText("October");
                break;
            case 11:
                holder.month.setText("November");
                break;
            case 12:
                holder.month.setText("December");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    class BusHolder extends RecyclerView.ViewHolder{
TextView month;
        public BusHolder(View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.bus_month);
        }
    }
}
