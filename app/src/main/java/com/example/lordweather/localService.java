package com.example.lordweather;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Binder;
import android.os.IBinder;

public class localService extends Service {
    private final IBinder binder = new LocalBinder();
   LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


    public class LocalBinder extends Binder {
        localService getService() {
            // Return this instance of LocalService so clients can call public methods
            return localService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent){
        return binder;
    }
    public void sendRequest(){
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }
}

