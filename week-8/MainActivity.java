
package com.example.intentmenuapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDial, btnWebsite, btnSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDial = findViewById(R.id.btnDial);
        btnWebsite = findViewById(R.id.btnWebsite);
        btnSMS = findViewById(R.id.btnSMS);

        // Option 1: Dial a Number
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1234567890"));
                startActivity(intent);
            }
        });

        // Option 2: Open a Website
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com"));
                startActivity(intent);
            }
        });

        // Option 3: Send an SMS
        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:1234567890"));
                intent.putExtra(
                    "sms_body",
                    "Hello! This is a test message."
                );

                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(
                        MainActivity.this,
                        "No SMS app found!",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}
