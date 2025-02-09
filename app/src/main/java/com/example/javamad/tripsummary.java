package com.example.javamad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

public class tripsummary extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView tvTotalTime;
    private MaterialButton btnContinue, btnFavorite, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ridesummary);

        // Initialize UI elements
        tvTotalTime = findViewById(R.id.tvTotalTime);
        btnContinue = findViewById(R.id.btnShare);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnSave = findViewById(R.id.btnSave);

        // Get data from Intent
        Intent intent = getIntent();
        String totalTime = intent.getStringExtra("TOTAL_TIME");
        tvTotalTime.setText(totalTime);

        // Load Google Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapPreview);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Handle button clicks
        btnContinue.setOnClickListener(v -> {
            Intent homeIntent = new Intent(tripsummary.this, home.class);
            startActivity(homeIntent);
            finish(); // Close current activity
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set trip end location marker (Example: New York)
        LatLng tripEndLocation = new LatLng(40.7128, -74.0060);
        mMap.addMarker(new MarkerOptions().position(tripEndLocation).title("Trip End Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tripEndLocation, 12));
    }
}
