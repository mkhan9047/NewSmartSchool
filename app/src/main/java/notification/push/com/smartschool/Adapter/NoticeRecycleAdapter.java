package notification.push.com.smartschool.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Models.Notice;
import notification.push.com.smartschool.R;

/**
 * Created by Mujahid on 5/24/2018.
 */

public class NoticeRecycleAdapter extends RecyclerView.Adapter<NoticeRecycleAdapter.MyViewHolder> {
    private List<Notice.Items> notices = new ArrayList<>();

    public NoticeRecycleAdapter(List<Notice.Items> notices) {
        this.notices = notices;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_reycle_custom_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.indicate.setText(String.valueOf(position+1));
        holder.title.setText(notices.get(position).getNotice_title());
        holder.description.setText(notices.get(position).getMessage());
        holder.date.setText(notices.get(position).getNotice_date());
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView indicate, title, description, date;
        public MyViewHolder(View itemView) {
            super(itemView);
            indicate = itemView.findViewById(R.id.notice_id_);
            title = itemView.findViewById(R.id.notice_title);
            description = itemView.findViewById(R.id.notice_des);
            date = itemView.findViewById(R.id.notice_date);

        }
    }
}
