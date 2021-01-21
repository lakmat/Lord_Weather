package com.example.lordweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;

public class inventory extends AppCompatActivity {
    SharedPreferences prefs;
    ArrayList<EditText> collection;
    ArrayList<String> nameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        Resources res = getResources();
        collection = new ArrayList<>();
        nameList = new ArrayList<>();
        //Adds dependencies between view and class
        for(int i = 0; i<12; i++){
            String idName = "item" + i + "num";
            collection.add((EditText) findViewById(res.getIdentifier(idName, "id", getPackageName())));
            nameList.add(idName);
        }
        prefs = getSharedPreferences("com.LordWeather", MODE_PRIVATE);

        for(int i =0; i<12; i++){
            //Sets current Inventory Numbers into the EditText elements
            collection.get(i).setText(String.valueOf(prefs.getInt(nameList.get(i), 2)));
            collection.get(0).setText(String.valueOf(prefs.getInt("item0num", 0)));
        }
    }
    public void onClick(View v){
        for(int i = 0; i<12; i++){
            collection.get(i).getText();
            //Saves the numbers entered into to sharedpreferences
            prefs.edit().putInt(nameList.get(i), Integer.parseInt(collection.get(i).getText().toString())).commit();
        }
        if (prefs.getBoolean("firstrun", true) == false) {
            Intent main = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(main);
        }
        else {
            Intent gtg = new Intent(getApplicationContext(), allset.class);
            startActivity(gtg);
        }

    }
}