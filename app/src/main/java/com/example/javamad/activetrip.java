package com.example.javamad;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

public class activetrip extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView tvTime;
    private MaterialButton btnEndTrip;

    private boolean isTimerRunning = false;
    private long elapsedTime = 0;
    private Handler timerHandler = new Handler();
    private long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activetrip);

        // Initialize UI elements
        tvTime = findViewById(R.id.tvTime);
        btnEndTrip = findViewById(R.id.btnEndTrip);

        // Initialize Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Start timer when activity starts
        startTimer();

        // Button click listener
        btnEndTrip.setOnClickListener(v -> stopTimerAndShowSummary());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set a fixed location marker (New York)
        LatLng newYork = new LatLng(40.7128, -74.0060);
        mMap.addMarker(new MarkerOptions().position(newYork).title("New York"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newYork, 12));
    }

    private void startTimer() {
        if (!isTimerRunning) {
            isTimerRunning = true;
            elapsedTime = 0;
            startTime = System.currentTimeMillis(); // Store start time
            timerHandler.postDelayed(updateTimerRunnable, 1000);
        }
    }

    private void stopTimerAndShowSummary() {
        if (isTimerRunning) {
            isTimerRunning = false;
            timerHandler.removeCallbacks(updateTimerRunnable);

            // Calculate total elapsed time
            long totalTimeMillis = System.currentTimeMillis() - startTime;
            long totalSeconds = totalTimeMillis / 1000;
            long hours = totalSeconds / 3600;
            long minutes = (totalSeconds % 3600) / 60;
            long seconds = totalSeconds % 60;

            String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            // Start Trip Summary Activity & pass data
            Intent intent = new Intent(activetrip.this, tripsummary.class);
            intent.putExtra("TOTAL_TIME", formattedTime);
            startActivity(intent);
            finish(); // Close this activity after finishing trip
        }
    }

    private final Runnable updateTimerRunnable = new Runnable() {
        @Override
        public void run() {
            if (isTimerRunning) {
                elapsedTime++;
                long hours = elapsedTime / 3600;
                long minutes = (elapsedTime % 3600) / 60;
                long seconds = elapsedTime % 60;
                tvTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

                timerHandler.postDelayed(this, 1000);
            }
        }
    };
}
