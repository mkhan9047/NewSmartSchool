package notification.push.com.smartschool.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import notification.push.com.smartschool.Adapter.ResultMarkAdapter;
import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Exam;
import notification.push.com.smartschool.Models.Marks;
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
public class ResultFragment extends Fragment {

    MaterialSpinner MTspinner;

    RecyclerView recyclerView;
    List<Marks.MarksList> marksLists;
    ResultMarkAdapter adapter;

    TextView resultYear, ResultName;
    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if(view != null ){
            MTspinner = view.findViewById(R.id.spinner_result);
            recyclerView = view.findViewById(R.id.result_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setHasFixedSize(true);
            resultYear = view.findViewById(R.id.result_year);
            ResultName = view.findViewById(R.id.result_name);
        }

        marksLists = new ArrayList<>();

        MTspinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Toast.makeText(getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
               getMarks(item);
            }
        });
        getExamNames();

        Stroage stroage = new Stroage(getActivity());
        ResultName.setText(String.format("Exam Results of %s",stroage.GetCurentUser()));





    }

    private void getExamNames(){
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Exam> getExam = retrofitInterface.getExamNames(stroage.GetCurentUserReg());
        getExam.enqueue(new Callback<Exam>() {
            @Override
            public void onResponse(Call<Exam> call, Response<Exam> response) {
                Exam exam = response.body();
                if(exam != null){

                    if(exam.getExam_names().size()>0){
                        resultYear.setText(String.format("Year: %s",exam.getExam_names().get(0).getYear()));
                        String[] arr = new String[exam.getExam_names().size()];
                        for(int i = 0; i < exam.getExam_names().size(); i++){
                            arr[i] = exam.getExam_names().get(i).getExam_type();
                            if(i==0){
                                getMarks(exam.getExam_names().get(i).getExam_type());
                            }
                        }
                        MTspinner.setItems(arr);
                    }



                }
            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {

            }
        });
    }

    private void getMarks(String examType){
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Getting Marks...");
        dialog.show();
        Stroage stroage = new Stroage(getActivity());
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Marks> getExam = retrofitInterface.getMarks(stroage.GetCurentUserReg(),examType);
        getExam.enqueue(new Callback<Marks>() {
            @Override
            public void onResponse(Call<Marks> call, Response<Marks> response) {
                Marks marks = response.body();
             if(marks != null){
            marksLists = marks.getMarks();

            marksLists.add(new Marks.MarksList("Grand Total", Helper.SumOffMarks(marksLists), Helper.SumOfTotalMark(marksLists)));

            adapter = new ResultMarkAdapter(marksLists);

            recyclerView.setAdapter(adapter);

            if(dialog.isShowing()){
                dialog.dismiss();
            }
             }
            }

            @Override
            public void onFailure(Call<Marks> call, Throwable t) {

            }
        });
    }
}
