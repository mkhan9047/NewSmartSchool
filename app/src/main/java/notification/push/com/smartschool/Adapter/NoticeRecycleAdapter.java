package notification.push.com.smartschool.Adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Dialog.NoticeDialogFragment;
import notification.push.com.smartschool.Models.Notice;
import notification.push.com.smartschool.R;
import notification.push.com.smartschool.Utility.Helper;

/**
 * Created by Mujahid on 5/24/2018.
 */

public class NoticeRecycleAdapter extends RecyclerView.Adapter<NoticeRecycleAdapter.MyViewHolder> {
    private List<Notice.Items> notices = new ArrayList<>();
    private FragmentManager manage;

    public NoticeRecycleAdapter(List<Notice.Items> notices, FragmentManager manage) {
        this.notices = notices;
        this.manage = manage;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_reycle_custom_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.indicate.setText(String.valueOf(position+1));
        holder.title.setText(Helper.getAbsolute(notices.get(position).getNotice_title()));
        holder.description.setText(Helper.getAbsolute(notices.get(position).getMessage()));
        holder.date.setText(notices.get(position).getNotice_date());
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView indicate, title, description, date;

        public MyViewHolder(View itemView) {

            super(itemView);

            itemView.setOnClickListener(this);
            indicate = itemView.findViewById(R.id.homework_id);
            title = itemView.findViewById(R.id.homework_title);
            description = itemView.findViewById(R.id.homework_des);
            date = itemView.findViewById(R.id.teacher_name);

        }

        @Override
        public void onClick(View view) {
            NoticeDialogFragment fragment = new NoticeDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",(Serializable) notices.get(getAdapterPosition()));
            fragment.setArguments(bundle);
            fragment.show(manage,null);
        }
    }
}
