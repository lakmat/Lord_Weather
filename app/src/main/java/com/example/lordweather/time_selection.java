package com.example.lordweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class time_selection extends AppCompatActivity {
    SharedPreferences prefs;
    EditText time;
    String time_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_selection);
        prefs = getSharedPreferences("com.LordWeather", MODE_PRIVATE);
        time = (EditText) findViewById(R.id.editTextTime);
        time.setText(prefs.getString("time", "00:00"));


    }
    public void onClick(View v){
        time_value = time.getText().toString();
        prefs.edit().putString("time", time_value).commit();
        if (prefs.getBoolean("firstrun", true) == false) {
            Intent main = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(main);
        }
        else {
            Intent clothesselstartup = new Intent(getApplicationContext(), com.example.lordweather.clothesselstartup.class);
            startActivity(clothesselstartup);
        }


    }
}
