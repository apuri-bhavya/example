
package com.example.gpslocationapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_CODE = 100;

    private LocationManager locationManager;
    private TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGetLocation = findViewById(R.id.btnGetLocation);
        tvLocation = findViewById(R.id.tvLocation);

        btnGetLocation.setOnClickListener(v -> getLocation());
    }

    private void getLocation() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_CODE
            );

        } else {
            try {
                locationManager = (LocationManager)
                        getSystemService(LOCATION_SERVICE);

                if (!locationManager.isProviderEnabled(
                        LocationManager.GPS_PROVIDER)) {

                    Toast.makeText(
                            this,
                            "Please enable GPS",
                            Toast.LENGTH_LONG
                    ).show();
                    return;
                }

                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0,
                        locationListener
                );

                tvLocation.setText("Getting location...");

            } catch (SecurityException e) {
                Toast.makeText(
                        this,
                        "Location permission required",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    private final LocationListener locationListener =
            new LocationListener() {

        @SuppressLint("SetTextI18n")
        @Override
        public void onLocationChanged(@NonNull Location location) {

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            tvLocation.setText(
                    "Latitude: " + latitude
                    + "\nLongitude: " + longitude
            );

            locationManager.removeUpdates(this);
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            Toast.makeText(
                    MainActivity.this,
                    "Please enable GPS",
                    Toast.LENGTH_LONG
            ).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults
        );

        if (requestCode == LOCATION_PERMISSION_CODE) {

            boolean granted = false;

            for (int i = 0; i < permissions.length; i++) {
                if ((Manifest.permission.ACCESS_FINE_LOCATION.equals(
                        permissions[i])
                        || Manifest.permission.ACCESS_COARSE_LOCATION.equals(
                        permissions[i]))
                        && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    granted = true;
                    break;
                }
            }

            if (granted) {
                getLocation();
            } else {
                Toast.makeText(
                        this,
                        "Location permission denied",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}
