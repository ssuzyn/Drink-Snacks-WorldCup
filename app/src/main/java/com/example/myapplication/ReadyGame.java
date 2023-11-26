package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ReadyGame {
    interface OnMenuInfoFetchedListener {
        void onMenuInfoFetched(List<MenuInfo> fetchedMenuList);
    }

    public void fetchMenuInfo(SQLiteDatabase db, int round, OnMenuInfoFetchedListener listener) {
        Cursor cursor = db.rawQuery("SELECT * FROM menu;", null);

        if (cursor != null && cursor.moveToFirst()) {
            int totalColumns = cursor.getCount();
            List<Integer> randomColumnIndexes = getRandomIndexes(totalColumns, round);
            List<MenuInfo> menuList = new ArrayList<>();

            do {
                MenuInfo menuInfo;
                for (int index : randomColumnIndexes) {
                    if (cursor.getPosition() == index) {
                        String menuName = cursor.getString(1);
                        String imagePath = cursor.getString(2);
                        menuInfo = new MenuInfo(menuName, imagePath);
                        menuList.add(menuInfo);
                    }
                }
            } while (cursor.moveToNext());
            cursor.close();

            new Handler().post(() -> listener.onMenuInfoFetched(menuList));
        } else {

            new Handler().post(() -> listener.onMenuInfoFetched(new ArrayList<>()));
        }
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
        return indexes;
    }
}
