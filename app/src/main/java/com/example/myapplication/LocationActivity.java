package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.DataBaseHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback{

    TextView textView;
    String menu;
    ImageButton btnHome;
    private List<LocationInfo> locationList;
    private GoogleMap mMap;
    private DataBaseHelper worldCupDB;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barlist);

        menu = getIntent().getStringExtra("menu");

        textView = (TextView) findViewById(R.id.textMenu);
        btnHome = (ImageButton) findViewById(R.id.btnHome);
        textView.setText("영남대 " + menu);

        worldCupDB = new DataBaseHelper(this);
        db = worldCupDB.getReadableDatabase();

        ReadyLocation readyMap = new ReadyLocation();

        readyMap.fetchLocationInfo(db, menu, fetchedMenuList -> {
            locationList = fetchedMenuList;

            runOnUiThread(() -> {
                printLocationList();
                SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
            });
        });


        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(LocationActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        if (locationList != null && !locationList.isEmpty()) {
            for (LocationInfo info : locationList) {
                LatLng barLocation = new LatLng(info.getLatitude(), info.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(barLocation);
                markerOptions.title(info.getBarName());

                mMap.addMarker(markerOptions);
            }

            LatLng firstLocation = new LatLng(locationList.get(0).getLatitude(), locationList.get(0).getLongitude());
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 16));
        }
    }


    private void printLocationList() {
        if (locationList != null && !locationList.isEmpty()) {
            for (LocationInfo info : locationList) {
                Log.d("LocationInfo", "술집 이름: " + info.getBarName() + ", 위도: " + info.getLatitude() + ", 경도: " + info.getLongitude());
            }
        } else {
            Log.d("LocationInfo", "LocationList is empty or null");
        }
    }
}
