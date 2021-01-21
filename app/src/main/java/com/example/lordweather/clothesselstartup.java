package com.example.lordweather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class clothesselstartup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clothessel_startup);
    }
    public void noClick(View v){
        Intent allset = new Intent(getApplicationContext(), com.example.lordweather.allset.class);
        startActivity(allset);
    }
    public void yesClick(View v){
        Intent inventory = new Intent(getApplicationContext(), allset.class);
        startActivity(inventory);

    }
}
