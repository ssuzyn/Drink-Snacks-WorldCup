package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RoundActivity extends AppCompatActivity {

    ImageButton btnRound16, btnRound8, btnRound4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        btnRound16 = (ImageButton) findViewById(R.id.btnRound16);
        btnRound8 = (ImageButton) findViewById(R.id.btnRound8);
        btnRound4 = (ImageButton) findViewById(R.id.btnRound4);

        Intent intent = new Intent(RoundActivity.this, WorldCupActivity.class);

        btnRound16.setOnClickListener(v -> {
            intent.putExtra("ROUND_NUMBER", 16); // 16 전달
            startActivity(intent);
        });

        btnRound8.setOnClickListener(v -> {
            intent.putExtra("ROUND_NUMBER", 8); // 8 전달
            startActivity(intent);
        });

        btnRound4.setOnClickListener(v -> {
            intent.putExtra("ROUND_NUMBER", 4); // 4 전달
            startActivity(intent);
        });



    }
}
