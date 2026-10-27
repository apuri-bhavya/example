
package com.example.openurlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUrl;
    Button btnOpenUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUrl = findViewById(R.id.editTextUrl);
        btnOpenUrl = findViewById(R.id.btnOpenUrl);

        btnOpenUrl.setOnClickListener(v -> {

            String url = editTextUrl.getText().toString().trim();

            if (url.isEmpty()) {
                Toast.makeText(
                        MainActivity.this,
                        "Please enter a URL",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Add https:// if missing
            if (!url.startsWith("http://")
                    && !url.startsWith("https://")) {
                url = "https://" + url;
            }

            try {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                );

                startActivity(intent);

            } catch (Exception e) {
                Toast.makeText(
                        MainActivity.this,
                        "Invalid URL!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
