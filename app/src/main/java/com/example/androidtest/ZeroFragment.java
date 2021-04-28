package com.example.androidtest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ZeroFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zero, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_nav = view.findViewById(R.id.btn_navegate);
        Button btn_res = view.findViewById(R.id.btn_resume);

        final NavController navController = Navigation.findNavController(view);

        btn_nav.setOnClickListener(v -> navController.navigate(R.id.firstFragment));
        btn_res.setOnClickListener(v -> navController.navigate(R.id.secondFragment));
    }
}