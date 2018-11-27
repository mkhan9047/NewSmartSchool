package notification.push.com.smartschool.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Models.OtherFee;
import notification.push.com.smartschool.R;
import notification.push.com.smartschool.Utility.Helper;

/**
 * Created by Mujahid on 7/16/2018.
 */

public class OtherFeeAdapter extends RecyclerView.Adapter<OtherFeeAdapter.ViewHolder> {

    public OtherFeeAdapter(List<String> feeList) {
        this.feeList = feeList;
    }

    private List<String> feeList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_fee_recyle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    holder.fee_name.setText(Helper.FirstPart(feeList.get(position)));
    if(TextUtils.isDigitsOnly(Helper.SecondPart(feeList.get(position)))){
        holder.fee_status.setText(Helper.KFormat(Double.parseDouble(Helper.SecondPart(feeList.get(position)))));
    }else{
        holder.fee_status.setText(Helper.SecondPart(feeList.get(position)));
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
