package notification.push.com.smartschool.Adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import notification.push.com.smartschool.Models.Marks;
import notification.push.com.smartschool.R;

/**
 * Created by Mujahid on 7/17/2018.
 */

public class ResultMarkAdapter extends RecyclerView.Adapter<ResultMarkAdapter.ResultHolder> {
    private List<Marks.MarksList> marksLists;

    public ResultMarkAdapter(List<Marks.MarksList> marksLists) {
        this.marksLists = marksLists;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_recyle,parent, false);
        return new ResultHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        holder.subject.setText(marksLists.get(position).getSubject_name());
        holder.mark.setText(String.format("%d/%d",marksLists.get(position).getMark(),marksLists.get(position).getFull_mark()));

    }



    @Override
    public int getItemCount() {
        return marksLists.size();
    }

    class ResultHolder extends  RecyclerView.ViewHolder{
        TextView subject, mark;
        public ResultHolder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.result_subject);
            mark = itemView.findViewById(R.id.result_mark);
        }
    }
}
