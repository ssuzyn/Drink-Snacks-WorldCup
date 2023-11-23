package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (ImageButton)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RoundActivity.class);
            startActivity(intent);
        });
    }
}