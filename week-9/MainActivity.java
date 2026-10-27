
package com.example.alarmapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView tvAlarmTime;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAlarmTime = findViewById(R.id.tvAlarmTime);
        Button btnSetTime = findViewById(R.id.btnSetTime);

        alarmManager = (AlarmManager)
                getSystemService(Context.ALARM_SERVICE);

        btnSetTime.setOnClickListener(v -> {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(
                            MainActivity.this,
                            (TimePicker view, int selectedHour,
                             int selectedMinute) -> {

                                calendar.set(
                                        Calendar.HOUR_OF_DAY,
                                        selectedHour
                                );
                                calendar.set(
                                        Calendar.MINUTE,
                                        selectedMinute
                                );
                                calendar.set(Calendar.SECOND, 0);
                                calendar.set(Calendar.MILLISECOND, 0);

                                // Set the alarm for the next occurrence
                                if (calendar.getTimeInMillis()
                                        <= System.currentTimeMillis()) {
                                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                                }

                                tvAlarmTime.setText(
                                        "Alarm set for: "
                                        + String.format(
                                                "%02d:%02d",
                                                selectedHour,
                                                selectedMinute
                                        )
                                );

                                setAlarm(calendar);
                            },
                            hour,
                            minute,
                            true
                    );

            timePickerDialog.show();
        });
    }

    private void setAlarm(Calendar targetCal) {
        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_IMMUTABLE
        );

        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                targetCal.getTimeInMillis(),
                pendingIntent
        );

        Toast.makeText(
                this,
                "Alarm Scheduled",
                Toast.LENGTH_SHORT
        ).show();
    }
}
