package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WorldCupGame {
    private List<MenuInfo> currentRoundMenus;
    private List<List<MenuInfo>> tournament;

    public WorldCupGame(List<MenuInfo> menuList) {
        this.currentRoundMenus = menuList;
        this.tournament = new ArrayList<>();
        printMenuList();
    }

    public void advanceRound(MenuInfo selectedMenu) {
        if (currentRoundMenus.contains(selectedMenu)) {
            List<MenuInfo> winners = new ArrayList<>();
            winners.add(selectedMenu);

            tournament.add(winners);

            if (currentRoundMenus.size() == 1) {
                Log.d("WorldCupGame", "현재 우승 메뉴: " + currentRoundMenus.get(0).getMenuName());
                prepareNextRound();
            }
        } else {
            Log.d("WorldCupGame", "선택한 메뉴가 현재 라운드에 없습니다.");
        }
    }

    private void prepareNextRound() {
        // 현재 라운드 우승자 목록을 가져옴
        List<MenuInfo> winners = tournament.get(tournament.size() - 1);

        List<MenuInfo> nextRoundMenus = new ArrayList<>();

        for (int i = 0; i < winners.size(); i += 2) {
            // winners에서 두 개씩 뽑아와 다음 라운드 메뉴로 구성
            MenuInfo firstMenu = winners.get(i);
            MenuInfo secondMenu = winners.get(i + 1);

            nextRoundMenus.add(firstMenu);
            nextRoundMenus.add(secondMenu);
        }

        currentRoundMenus = nextRoundMenus;
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