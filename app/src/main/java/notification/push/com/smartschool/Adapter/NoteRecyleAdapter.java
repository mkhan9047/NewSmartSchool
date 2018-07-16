package notification.push.com.smartschool.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Dialog.NoteDailogFragment;
import notification.push.com.smartschool.Models.Notes;
import notification.push.com.smartschool.R;
import notification.push.com.smartschool.Utility.Helper;

/**
 * Created by Mujahid on 5/26/2018.
 */

public class NoteRecyleAdapter extends RecyclerView.Adapter<NoteRecyleAdapter.MyViewHolder> {
    private List<Notes.NoteItems> notes = new ArrayList<>();
    private Context context;
    Fragment fragment;
    private FragmentManager fragmentManager;
    public NoteRecyleAdapter(List<Notes.NoteItems> notes, Context context, FragmentManager fragment) {
        this.notes = notes;
        this.context = context;
        this.fragmentManager = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_recycle_custom, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.indicate.setText(String.valueOf(position + 1));
        holder.title.setText(Helper.getAbsolute(notes.get(position).getNote_title()));
        holder.description.setText(Helper.getAbsolute(notes.get(position).getMessage()));
        holder.teacher_name.setText(Helper.getAbsolute(notes.get(position).getTeacher_name()));
        holder.date.setText(notes.get(position).getNote_date());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


     class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView indicate, title, description, date, teacher_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            indicate = itemView.findViewById(R.id.homework_id);
            title = itemView.findViewById(R.id.homework_title);
            description = itemView.findViewById(R.id.homework_des);
            teacher_name = itemView.findViewById(R.id.teacher_name);
            date = itemView.findViewById(R.id.note_date);

        }


         @Override
         public void onClick(View view) {
             NoteDailogFragment fragment = new NoteDailogFragment();
             Bundle bundle = new Bundle();
             bundle.putSerializable("data",(Serializable) notes.get(getAdapterPosition()));
             fragment.setArguments(bundle);
             fragment.show(fragmentManager,null);
         }
     }

}