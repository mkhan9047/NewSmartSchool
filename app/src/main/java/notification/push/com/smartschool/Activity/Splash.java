package notification.push.com.smartschool.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        int secondsDelayed = 1;
        final Stroage stroage = new Stroage(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(stroage.getLogInState()){
                    startActivity(new Intent(Splash.this, Dashboard.class));
                    finish();
                }
                startActivity(new Intent(Splash.this, LogInActivity.class));
                finish();
            }
        }, secondsDelayed * 1500);
    }
}
