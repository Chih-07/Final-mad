package com.example.javamad.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javamad.R;
import com.example.javamad.Register;
import com.example.javamad.activetrip;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {

    private MaterialCardView cardMileage, cardCO2, cardStartRide, cardHistory, cardStats, cardSocial;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize CardViews
        cardMileage = view.findViewById(R.id.cardMileage);
        cardCO2 = view.findViewById(R.id.cardCO2);
        cardStartRide = view.findViewById(R.id.cardStartRide);
        cardHistory = view.findViewById(R.id.cardHistory);
        cardStats = view.findViewById(R.id.cardStats);
        cardSocial = view.findViewById(R.id.cardSocial);

        // Set Click Listeners
        cardMileage.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Mileage Details clicked", Toast.LENGTH_SHORT).show());

        cardCO2.setOnClickListener(v ->
                Toast.makeText(getActivity(), "CO2 Details clicked", Toast.LENGTH_SHORT).show());

        cardStartRide.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Start Ride clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), activetrip.class);
            startActivity(intent);
        });

        cardHistory.setOnClickListener(v ->
                Toast.makeText(getActivity(), "History clicked", Toast.LENGTH_SHORT).show());

        cardStats.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Statistics clicked", Toast.LENGTH_SHORT).show());

        cardSocial.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Social clicked", Toast.LENGTH_SHORT).show());

        return view;
    }
}
