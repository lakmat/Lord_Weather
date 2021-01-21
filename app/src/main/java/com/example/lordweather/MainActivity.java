package com.example.lordweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs = null;
    TextView welcomeText;
    localService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, localService.class));
        prefs = getSharedPreferences("com.LordWeather", MODE_PRIVATE);
        SharedPreferences sharedPref = getSharedPreferences("com.LordWeather", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).commit();
            Intent firsts = new Intent(getApplicationContext(), firststartup.class);
            startActivity(firsts);
        }
        welcomeText = (TextView) findViewById(R.id.welcome_text);
        welcomeText.setText("Welcome " + prefs.getString("name", "") + "!");


    }
    protected void onStart(){
        super.onStart();

     //   Intent intent = new Intent(this, localService.class);
      //  bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).commit();
            Intent firsts = new Intent(getApplicationContext(), firststartup.class);
            startActivity(firsts);
        }

    }
    public void clickClo(View v){
        Intent invent = new Intent(getApplicationContext(), inventory.class);
        startActivity(invent);
    }
    public void clickTime(View v){
        Intent clock = new Intent(getApplicationContext(), time_selection.class);
        startActivity(clock);
    }

   // private ServiceConnection connection = new ServiceConnection() {
     //   @Override
     //   public void onServiceConnected(ComponentName className,
      //                                 IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
      //      localService.LocalBinder binder = (localService.LocalBinder) service;
      //       mService= binder.getService();
      //      mBound = true;
     //   }

     //   @Override
   //     public void onServiceDisconnected(ComponentName name) {
     //       mBound = false;
        }
   // };
