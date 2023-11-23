package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class WorldCupActivity extends AppCompatActivity {

    int round;
    DataBaseHelper worldCupDB;
    SQLiteDatabase db;
    List<MenuInfo> menuList;
    List<Integer> randomColumnIndexes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worldcup);

        round = getIntent().getIntExtra("ROUND_NUMBER", 0);
        Log.d("WorldCupActivity", round + "round start");

        worldCupDB = new DataBaseHelper(WorldCupActivity.this);
        db = worldCupDB.getWritableDatabase();

        // Run database operation in a new thread
        new Thread(() -> {
            getMenuByRound();
        }).start();
    }

    private void getMenuByRound() {
        Cursor cursor = db.rawQuery("SELECT * FROM menu;", null);

        if (cursor != null && cursor.moveToFirst()) {
            int totalColumns = cursor.getCount();
            Log.d("WorldCupActivity","DB select Record : " + totalColumns);

            randomColumnIndexes = getRandomIndexes(totalColumns, round);
            menuList = new ArrayList<>();

            do {
                MenuInfo menuInfo;
                for (int index : randomColumnIndexes) {
                    if(cursor.getPosition() == index){
                        String menuName = cursor.getString(1);
                        String imagePath = cursor.getString(2);
                        menuInfo = new MenuInfo(menuName, imagePath);
                        menuList.add(menuInfo);
                    }
                }
            } while (cursor.moveToNext());
            Log.d("WorldCupActivity","Menu information list complete");
            cursor.close();
        } else {
            Log.d("Menu", "No data available");
        }

        runOnUiThread(() -> {
            // UI-related operations after database operation
            printMenuList();
        });
    }

    private List<Integer> getRandomIndexes(int totalColumns, int limit) {
        List<Integer> indexes = new ArrayList<>();
        Random random = new Random();

        while (indexes.size() < limit) {
            int randomIndex = random.nextInt(totalColumns);
            if (!indexes.contains(randomIndex)) {
                indexes.add(randomIndex);
            }
        }
        Collections.sort(indexes);
        Log.d("WorldCupActivity", "random number size : " + indexes.size());
        return indexes;
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
