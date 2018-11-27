package notification.push.com.smartschool.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.LogIn;
import notification.push.com.smartschool.Networking.RetrofitClient;
import notification.push.com.smartschool.Networking.RetrofitInterface;
import notification.push.com.smartschool.R;
import notification.push.com.smartschool.Utility.Constants;
import notification.push.com.smartschool.Utility.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    EditText reg_no, password;
    Button btn_login;
    TextView forgot_pass, indicate;
    Stroage stroage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //views intiliztion
        InitView();
        input_validation();

    }

    private void InitView() {
        reg_no = findViewById(R.id.reg_no);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        indicate = findViewById(R.id.indicate);
    }

    private void input_validation(){
        reg_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (reg_no.getText().toString().length() <= 0) {
                    reg_no.setError("Enter Reg No!");
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (password.getText().toString().length() <= 0) {
                    password.setError("Enter Password!");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLogin(View view) {
        if (reg_no.getText().toString().trim().equalsIgnoreCase("")) {
            reg_no.setError("This field can not be blank!");
        }
        if (password.getText().toString().trim().equalsIgnoreCase("")) {
            password.setError("This field can not be blank!");
        }
/*
        if(reg_no.getText().toString().contains(Constants.REG_NO)  && password.getText().toString().contains(Constants.PSSWORD)){
            indicate.setVisibility(View.GONE);
            stroage.SaveLogInSate(true);
            stroage.SaveCurrentUSer("Mujahid Khan");
            Intent intent = new Intent(this, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else{
            if(!(reg_no.getText().toString().length()==0)&&!(password.getText().toString().length()==0))
            indicate.setVisibility(View.VISIBLE);
        }
        */


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging In.....");
        progressDialog.show();
        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<LogIn> data =  retrofitInterface.getRepos(reg_no.getText().toString(),password.getText().toString());
        data.enqueue(new Callback<LogIn>() {
            @Override
            public void onResponse(Call<LogIn> call, Response<LogIn> response) {
                LogIn logIn = response.body();
                if(logIn!=null){

                    if(logIn.getSuccess()==1){

                        indicate.setVisibility(View.GONE);
                        stroage.SaveLogInSate(true);
                        stroage.SaveStudentPhoto(logIn.getStudent_photo());
                        stroage.SavePassword(logIn.getPassword());
                        //Log.d("from_server",logIn.getStudent_photo());
                        stroage.SaveUserRegNo(logIn.getReg_no());
                        stroage.SaveCurrentUSer(logIn.getStudent().get(0).getStudent_name());

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        //intent.putExtra("profile",(Serializable) logIn.getStudent().get(0));
                        startActivity(intent);
                        finish();

                    }else{

                        indicate.setVisibility(View.VISIBLE);
                    }

                    if(progressDialog.isShowing()){

                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<LogIn> call, Throwable t) {
                Log.d("networkError",t.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        stroage = new Stroage(this);
        if(stroage.getLogInState()){
            Intent intent = new Intent(this, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}
