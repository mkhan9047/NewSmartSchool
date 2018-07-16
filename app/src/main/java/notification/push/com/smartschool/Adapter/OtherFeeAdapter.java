package notification.push.com.smartschool.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Models.OtherFee;
import notification.push.com.smartschool.R;

/**
 * Created by Mujahid on 7/16/2018.
 */

public class OtherFeeAdapter extends RecyclerView.Adapter<OtherFeeAdapter.ViewHolder> {

    public OtherFeeAdapter(List<OtherFee.Fees> feeList) {
        this.feeList = feeList;
    }

    private List<OtherFee.Fees> feeList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_fee_recyle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.fee_name.setText(feeList.get(position).getFee_name());

        if(feeList.get(position).getStatus()==0){

            holder.fee_status.setText("UNPAID");

        }else if(feeList.get(position).getStatus()==1){

            holder.fee_status.setText("PAID");

        }
    }

    @Override
    public int getItemCount() {
        return feeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView fee_name, fee_status;
        public ViewHolder(View itemView) {
            super(itemView);
            fee_name = itemView.findViewById(R.id.fee_type);
            fee_status = itemView.findViewById(R.id.fee_status);
        }
    }
}
