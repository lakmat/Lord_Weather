package com.example.lordweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class firststartup extends AppCompatActivity {

    SharedPreferences prefs;
    private String name;
    EditText name_box;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_startup);
        name_box = (EditText) findViewById(R.id.enterNameBox);
        prefs = getSharedPreferences("com.LordWeather", MODE_PRIVATE);

    }
    public void oncontclick(View v){
        name = name_box.getText().toString();
        prefs.edit().putString("name", name).commit();
        Intent time_selection = new Intent(getApplicationContext(), com.example.lordweather.time_selection.class);
        startActivity(time_selection);
    }

}
