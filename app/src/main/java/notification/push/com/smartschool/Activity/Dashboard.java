package notification.push.com.smartschool.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import notification.push.com.smartschool.Dialog.NoteDailogFragment;
import notification.push.com.smartschool.Fragment.FragmentDashborad;
import notification.push.com.smartschool.Fragment.FragmentNotice;
import notification.push.com.smartschool.Fragment.HolidayCalender;
import notification.push.com.smartschool.Fragment.HomeworkFragment;
import notification.push.com.smartschool.Fragment.Note;
import notification.push.com.smartschool.LocalStroage.Stroage;
import notification.push.com.smartschool.Models.Student;
import notification.push.com.smartschool.R;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Student student;
    TextView user_name, user_reg;
    Stroage stroage;
    Fragment currentFragment;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*
        Intent i = getIntent();
        student = (Student) i.getSerializableExtra("profile");
        Stroage stroage = new Stroage(this);
        stroage.SaveCurrentUSer(student.getStudent_name());
*/

        stroage = new Stroage(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        user_name = header.findViewById(R.id.user_name);
        user_reg = header.findViewById(R.id.user_reg);
        user_name.setText(stroage.GetCurentUser());
        user_reg.setText(stroage.GetCurentUserReg());
        navigationView.setNavigationItemSelectedListener(this);

        currentFragment = new FragmentDashborad();
        FragmentTransction();
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Dashboard");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        }else if(!(currentFragment instanceof FragmentDashborad)){

            currentFragment = new FragmentDashborad();
            FragmentTransction();
            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle("Dashboard");
            }

        }else {

            final android.support.v7.app.AlertDialog.Builder dailog = new android.support.v7.app.AlertDialog.Builder(this);
            dailog.setMessage("Do you want to exit?");
            dailog.setIcon(R.mipmap.ic_launcher_round);
            dailog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });

            dailog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dailog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
         stroage = new Stroage(this);
        if(id==R.id.nav_log_out){
            stroage.SaveLogInSate(false);
            Intent intent = new Intent(this, LogInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else if(id==R.id.nav_notification){
            currentFragment = new FragmentNotice();
            FragmentTransction();
            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle("Notification");
            }
        }else if(id==R.id.nav_dashboard){
            currentFragment = new FragmentDashborad();
            FragmentTransction();
            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle("Dashboard");
            }
        }else if(id==R.id.nav_notes){
            currentFragment = new Note();
            FragmentTransction();
            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle("Notes");
            }
        }else if(id==R.id.nav_fees){
            NoteDailogFragment fragment = new NoteDailogFragment();
            fragment.show(getSupportFragmentManager(),null);
        }else if(id == R.id.nav_homework){
            currentFragment = new HomeworkFragment();
            FragmentTransction();
            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle("Homework");
            }
        }else if(id == R.id.nav_holiday_caldender){
            currentFragment = new HolidayCalender();
            FragmentTransction();
            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle("Holidays");
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void FragmentTransction(){
       FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.trans_frame,currentFragment);
        fragmentTransaction.commit();
    }
    public void FragmentTransction(Fragment fragment, String text, int id){
        currentFragment = fragment;
       FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.trans_frame,fragment);
        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_ENTER_MASK);
        fragmentTransaction.commit();
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(text);
        }
        navigationView.setCheckedItem(id);

    }
}
