package week-3;
package com.example.nameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView textViewDetails;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        textViewDetails = findViewById(R.id.textViewDetails);
        buttonBack = findViewById(R.id.buttonBack);

        // Get passed details from Intent
        String detail = getIntent().getStringExtra("details");

        textViewDetails.setText(detail);

        buttonBack.setOnClickListener(v -> finish());
    }
}
