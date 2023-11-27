package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.database.DataBaseHelper;

import java.util.List;


public class WorldCupActivity extends AppCompatActivity {

    private int round;
    private DataBaseHelper worldCupDB;
    private SQLiteDatabase db;
    private List<MenuInfo> menuList;
    private WorldCupGame worldCupGame;

    private ImageView menuImage1;
    private ImageView menuImage2;


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
                worldCupGame = new WorldCupGame(menuList);
                updateUI();
            });
        });

        menuImage1 = (ImageView) findViewById(R.id.menuImage1);
        menuImage2 = (ImageView) findViewById(R.id.menuImage2);


        menuImage1.setOnClickListener(v -> {
            MenuInfo selectedMenu = menuList.get(0); // 사용자가 선택한 첫 번째 이미지에 해당하는 메뉴
            worldCupGame.advanceRound(selectedMenu); // WorldCupGame 클래스에서 다음 라운드로 진행하는 메서드 호출
            updateUI(); // UI 업데이트를 위한 메서드 호출
        });

        menuImage2.setOnClickListener(v -> {
            MenuInfo selectedMenu = menuList.get(1); // 사용자가 선택한 두 번째 이미지에 해당하는 메뉴
            worldCupGame.advanceRound(selectedMenu); // WorldCupGame 클래스에서 다음 라운드로 진행하는 메서드 호출
            updateUI(); // UI 업데이트를 위한 메서드 호출
        });
    }

    private void updateUI(){
        if (menuList != null && menuList.size() >= 2) {
            MenuInfo menu1 = menuList.get(0);
            MenuInfo menu2 = menuList.get(1);

            int resourceId1 = ImageResourceHelper.getImageResourceId(this, menu1.getImagePath());
            int resourceId2 = ImageResourceHelper.getImageResourceId(this, menu2.getImagePath());
            Log.d("ImageResource", "Resource ID 1: " + resourceId1);
            Log.d("ImageResource", "Resource ID 2: " + resourceId2);

            menuImage1.setImageResource(resourceId1);
            menuImage2.setImageResource(resourceId2);

            Log.d("updateUI", "다음 토너먼트 진행");

            menuImage1.invalidate();
            menuImage2.invalidate();

        } else {
            Log.d("updateUI", "메뉴 리스트가 비어있거나 메뉴가 하나 이하 입니다.");
        }
    }

}
