package com.example.lordweather;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class localService extends Service {
    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";
    LocationManager locationManager;
    String latitude, longitude;
    SharedPreferences prefs;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    String time;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // do your jobs here
        prefs = getSharedPreferences("com.LordWeather", MODE_PRIVATE);

        alarmMgr = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent2 = new Intent(this, alarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent2, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        time = prefs.getString("time", "");
        String[] parts = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(parts[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(parts[1]));
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, alarmIntent );



// Access the RequestQueue through your singleton class.
        //  MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        return super.onStartCommand(intent, flags, startId);
    }

    private void setData(){
        String url = "https://api.openweathermap.org/data/2.5/onecall?lat="+latitude +"&lon="+ longitude+"&exclude=minutely,hourly,events,alerts,current&appid=d4715018895492522b0f22e8ac237412";
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // textView.setText("Response: " + response.toString());
                        try {
                            JSONArray daily = response.getJSONArray("daily");
                            JSONObject obj = daily.getJSONObject(0);
                            String humid = obj.getString("humidity");
                            JSONObject temp = obj.getJSONObject("temp");
                            String temp_max = temp.getString("max");
                            String temp_min = temp.getString("min");
                            JSONObject weather = obj.getJSONObject("weather");
                            String weather_desc = weather.getString("main");

                            prefs.edit().putString("humid", humid).commit();
                            prefs.edit().putString("temp_max", temp_max).commit();
                            prefs.edit().putString("temp_min", temp_min).commit();
                            prefs.edit().putString("weather_desc", weather_desc).commit();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                localService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                localService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
         //   ActivityCompat.requestPermissions(MainActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void calculate(){

    }

}
