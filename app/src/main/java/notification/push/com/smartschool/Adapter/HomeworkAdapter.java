package notification.push.com.smartschool.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Dialog.HomeworkDialogFragment;
import notification.push.com.smartschool.Dialog.NoteDailogFragment;
import notification.push.com.smartschool.Models.Homework;
import notification.push.com.smartschool.R;
import notification.push.com.smartschool.Utility.Helper;

/**
 * Created by Mujahid on 5/27/2018.
 */

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.MyViewHolder> {

    private List<Homework.Works> homeworks = new ArrayList<>();
    private FragmentManager manager;
    private Context context;
    public HomeworkAdapter(List<Homework.Works> homeworks, FragmentManager manager, Context context) {
        this.homeworks = homeworks;
        this.manager = manager;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homework_custom_recycle,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {
    holder.id.setText(String.valueOf(position+1));
    holder.des.setText(Helper.getAbsolute(homeworks.get(position).getHomework_detail()));
    holder.date.setText(homeworks.get(position).getHomework_date());
    holder.teacher_name.setText(String.format("From: %s",Helper.getAbsolute(homeworks.get(position).getTeacher_name())));
    if(homeworks.get(position).getFile_name().length()>0){
        holder.download.setVisibility(View.VISIBLE);
    }


    }

    @Override
    public int getItemCount() {
        return homeworks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView id, des, date, teacher_name;
        ImageView download;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            id = itemView.findViewById(R.id.homework_id);
            des = itemView.findViewById(R.id.homework_des);
            teacher_name = itemView.findViewById(R.id.homework_teacher);
            date = itemView.findViewById(R.id.homework_date);
            download = itemView.findViewById(R.id.download_img);
            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Helper.downlaodFile(context,homeworks.get(getAdapterPosition()).getFile_name());
                }
            });

        }

        @Override
        public void onClick(View view) {
            HomeworkDialogFragment fragment = new HomeworkDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",(Serializable) homeworks.get(getAdapterPosition()));
            fragment.setArguments(bundle);
            fragment.show(manager,null);
        }
    }
}
