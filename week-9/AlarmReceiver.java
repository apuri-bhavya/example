
package com.example.alarmapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(
                context,
                "Alarm! Wake up!",
                Toast.LENGTH_LONG
        ).show();

        // You can also trigger a notification here.
    }
}
