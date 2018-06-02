package notification.push.com.smartschool.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import notification.push.com.smartschool.Models.Notice;
import notification.push.com.smartschool.R;

/**
 * Created by Mujahid on 5/27/2018.
 */

public class NoticeDialogFragment extends DialogFragment {
    TextView note_id, note_title, note_message,  note_date, note_sending_date;
    Notice.Items items;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dailog_custom_notes, container, false);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View in = inflater.inflate(R.layout.notice_dialog_layout, null);
        Bundle a = getArguments();
        items = (Notice.Items) a.getSerializable("data");
        note_id = in.findViewById(R.id.dg_homework_id);
        note_title = in.findViewById(R.id.dg_notice_title);
        note_message = in.findViewById(R.id.dg_homework_message);
        note_date = in.findViewById(R.id.dg_homework_date);
        note_sending_date = in.findViewById(R.id.dg_homework_sending_date);

        note_id.setText(String.format("Note ID: %d",items.getNotice_id()));
        note_title.setText(items.getNotice_title());
        note_message.setText(items.getMessage());
        note_date.setText(String.format("%s",items.getNotice_date()));
        note_sending_date.setText(String.format("%s",items.getCreated_date()));


        builder.setView(in);
        builder.setNeutralButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder.create();
    }
}
