package notification.push.com.smartschool.Activity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Student;
import notification.push.com.smartschool.Networking.RetrofitClient;
import notification.push.com.smartschool.Networking.RetrofitInterface;
import notification.push.com.smartschool.R;
import notification.push.com.smartschool.Utility.Constants;
import notification.push.com.smartschool.Utility.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    CircleImageView image;
    TextView roll_number, gender, class_name, name, section;
    TextView father, address, bus, contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        InitView();
        getStudentInfo();

    }

    private void InitView(){
        name = findViewById(R.id.name);
        image = findViewById(R.id.photo);
        roll_number = findViewById(R.id.roll_number);
        gender = findViewById(R.id.gender);
        class_name = findViewById(R.id.class_name);
        father = findViewById(R.id.father_name);
        address = findViewById(R.id.address);
        bus = findViewById(R.id.bus);
        contact = findViewById(R.id.phonenum);
        section =findViewById(R.id.section);
    }


    private void getStudentInfo(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Getting Info....");
        dialog.show();
        final Stroage stroage = new Stroage(this);
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Student> StudentData =  retrofitInterface.getStudentInfo(stroage.GetCurentUserReg());
        StudentData.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Student student = response.body();
if(student!=null){
    name.setText(stroage.GetCurentUser());
    roll_number.setText(String.valueOf(student.getStudent_roll()));
    gender.setText(String.format("%s",student.getGender().substring(0,1).toUpperCase() + student.getGender().substring(1).toLowerCase()));
    class_name.setText(student.getClass_name());
    father.setText(Helper.getAbsolute(student.getFather_name()));
    address.setText(Helper.getAbsolute(student.getPresent_address()));
    section.setText(String.format("Section: %s",student.getSection()));
    contact.setText(Helper.getAbsolute(student.getContact_number()));
    if(student.getBus_service()==1){
        bus.setText("Yes, Using Bus Service");
    }else if(student.getBus_service() == 0){
        bus.setText("No, Not Using Bus Service");
    }

if(!isDestroyed()){
if(student.getStudent_photo()==null){
    Glide.with(Profile.this).load(Constants.base_stu_img+stroage.GetStudentPhoto()).into(image);
}else{
    Glide.with(Profile.this).load(Constants.base_stu_img+student.getStudent_photo()).into(image);
}

}
if(dialog.isShowing()){
    dialog.dismiss();
}

}
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });
    }
}
