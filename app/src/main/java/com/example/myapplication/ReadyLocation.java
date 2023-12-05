package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

public class ReadyLocation {

    interface OnLocationInfoFetchedListener {
        void onLocationInfoFetched(List<LocationInfo> fetchedLocationList);
    }

    public void fetchLocationInfo(SQLiteDatabase db, String menuName, OnLocationInfoFetchedListener listener) {
        String query = "SELECT * FROM bar WHERE bar_id IN (SELECT bar_id FROM bar_menu WHERE menu_id = (SELECT menu_id FROM menu WHERE menu_name = ?));";

        Cursor cursor = db.rawQuery(query, new String[]{menuName});

        if (cursor != null && cursor.moveToFirst()) {
            List<LocationInfo> locationList = new ArrayList<>();

            do {
                String barName = cursor.getString(1);
                double latitude = cursor.getDouble(2);
                double longitude = cursor.getDouble(3);
                LocationInfo locationInfo = new LocationInfo(barName, latitude, longitude);
                locationList.add(locationInfo);
            } while (cursor.moveToNext());

            cursor.close();

            new Handler().post(() -> listener.onLocationInfoFetched(locationList));
        } else {
            new Handler().post(() -> listener.onLocationInfoFetched(new ArrayList<>()));
        }
    }

}
