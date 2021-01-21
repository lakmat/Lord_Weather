package com.example.lordweather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.ContextWrapper;

public class alarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Intent serviceIntent = new Intent(context, localService.class);
                context.startService(serviceIntent);
        }
    }
}
