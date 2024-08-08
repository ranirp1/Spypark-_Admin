package com.example.spypark_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class flashscreen extends AppCompatActivity {
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashscreen);

        h.postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}