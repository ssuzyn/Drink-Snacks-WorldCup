package com.example.myapplication;

public class LocationInfo {

    private String barName;
    private double latitude;
    private double longitude;

    public LocationInfo(String barName, double latitude, double longitude){
        this.barName = barName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getBarName() {
        return barName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
