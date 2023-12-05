package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChampionshipActivity extends AppCompatActivity {

    private ImageView championMenuImage;
    private ImageButton btnBarList, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_championship);

        championMenuImage = (ImageView) findViewById(R.id.championImage);
        btnBarList = (ImageButton) findViewById(R.id.btnBarList);
        btnHome = (ImageButton) findViewById(R.id.btnHome);

        MenuInfo winningMenu = (MenuInfo) getIntent().getSerializableExtra("WinningMenu");
        if (winningMenu != null) {
            Log.d("Championship", "최종 우승 메뉴 : " + winningMenu.getMenuName());

            int resourceId = ImageResourceHelper.getImageResourceId(this, winningMenu.getImagePath());
            championMenuImage.setImageResource(resourceId);
            championMenuImage.invalidate();
        }

        btnBarList.setOnClickListener(view -> {
            Intent intent = new Intent(ChampionshipActivity.this, LocationActivity.class);
            intent.putExtra("menu", winningMenu.getMenuName());
            startActivity(intent);
        });

        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(ChampionshipActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
