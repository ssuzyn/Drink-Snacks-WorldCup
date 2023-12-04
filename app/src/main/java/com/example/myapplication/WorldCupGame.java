package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WorldCupGame {
    private List<MenuInfo> currentRoundMenus;
    private List<MenuInfo> tournament;

    public WorldCupGame(List<MenuInfo> menuList) {
        this.currentRoundMenus = menuList;
        this.tournament = new ArrayList<>();
        printMenuList();
    }

    public void advanceRound(MenuInfo selectedMenu) {
        if (currentRoundMenus.contains(selectedMenu)) {

            tournament.add(selectedMenu);

            Log.d("WorldCupGame", "현재 우승 메뉴: " + selectedMenu.getMenuName());
        } else {
            Log.d("WorldCupGame", "선택한 메뉴가 현재 라운드에 없습니다.");
        }
    }

    public List<MenuInfo> prepareNextRound() {
        List<MenuInfo> nextRoundMenus = new ArrayList<>(tournament);
        currentRoundMenus.clear();
        currentRoundMenus.addAll(nextRoundMenus);
        Log.d("worldCupGame", String.valueOf(currentRoundMenus.size()));
        tournament.clear();
        return currentRoundMenus;
    }

    private void printMenuList() {
        if (currentRoundMenus != null && !currentRoundMenus.isEmpty()) {
            for (MenuInfo menuInfo : currentRoundMenus) {
                Log.d("MenuInfo", "Menu Name: " + menuInfo.getMenuName() + ", Image Path: " + menuInfo.getImagePath());
            }
        } else {
            Log.d("MenuInfo", "MenuList is empty or null");
        }
    }
}