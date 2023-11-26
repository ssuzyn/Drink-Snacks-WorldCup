package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.DataBaseHelper;

import java.util.List;


public class WorldCupActivity extends AppCompatActivity {

    private int round;
    private DataBaseHelper worldCupDB;
    private SQLiteDatabase db;
    private List<MenuInfo> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worldcup);

        round = getIntent().getIntExtra("ROUND_NUMBER", 0);
        Log.d("WorldCupActivity", round + "round start");

        worldCupDB = new DataBaseHelper(WorldCupActivity.this);
        db = worldCupDB.getWritableDatabase();

        ReadyGame readyGame = new ReadyGame();
        readyGame.fetchMenuInfo(db, round, fetchedMenuList -> {
            menuList = fetchedMenuList;

            runOnUiThread(() -> {
                printMenuList();
            });
        });

    }

    private void printMenuList() {
        if (menuList != null && !menuList.isEmpty()) {
            for (MenuInfo menuInfo : menuList) {
                Log.d("MenuInfo", "Menu Name: " + menuInfo.getMenuName() + ", Image Path: " + menuInfo.getImagePath());
            }
        } else {
            Log.d("MenuInfo", "MenuList is empty or null");
        }
    }

}
