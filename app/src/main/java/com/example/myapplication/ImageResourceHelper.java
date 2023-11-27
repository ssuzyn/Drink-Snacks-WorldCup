package com.example.myapplication;

import android.content.Context;

public class ImageResourceHelper {

    public static int getImageResourceId(Context context, String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

}
