package com.example.lordweather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class allset extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_set);

    }
    public void goHome(View v){
        Intent main = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(main);
    }

}
