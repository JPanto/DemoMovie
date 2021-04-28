package com.example.androidtest.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.androidtest.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed((Runnable) () -> {
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            finish();
        },2000);
    }
}