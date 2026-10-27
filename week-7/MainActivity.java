package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private static final String default_notification_channel_id = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btnCreateNotification =
                findViewById(R.id.btnCreateNotification);

        btnCreateNotification.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("NotificationPermission")
            @Override
            public void onClick(View v) {

                Uri sound = Uri.parse(
                        ContentResolver.SCHEME_ANDROID_RESOURCE
                                + "://"
                                + getPackageName()
                                + "/raw/quite_impressed.mp3"
                );

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(
                                MainActivity.this,
                                NOTIFICATION_CHANNEL_ID
                        )
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setContentTitle("Test")
                                .setSound(sound)
                                .setContentText(
                                        "Hello! This is my first push notification"
                                );

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(
                                Context.NOTIFICATION_SERVICE
                        );

                if (android.os.Build.VERSION.SDK_INT
                        >= android.os.Build.VERSION_CODES.O) {

                    AudioAttributes audioAttributes =
                            new AudioAttributes.Builder()
                                    .setContentType(
                                            AudioAttributes.CONTENT_TYPE_SONIFICATION
                                    )
                                    .setUsage(AudioAttributes.USAGE_ALARM)
                                    .build();

                    int importance = NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel notificationChannel =
                            new NotificationChannel(
                                    NOTIFICATION_CHANNEL_ID,
                                    "NOTIFICATION_CHANNEL_NAME",
                                    importance
                            );

                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationChannel.enableVibration(true);

                    notificationChannel.setVibrationPattern(
                            new long[]{
                                    100, 200, 300, 400, 500,
                                    400, 300, 200, 400
                            }
                    );

                    notificationChannel.setSound(sound, audioAttributes);

                    mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);

                    assert mNotificationManager != null;

                    mNotificationManager.createNotificationChannel(
                            notificationChannel
                    );
                }

                Objects.requireNonNull(mNotificationManager).notify(
                        (int) System.currentTimeMillis(),
                        mBuilder.build()
                );
            }
        });
    }
}