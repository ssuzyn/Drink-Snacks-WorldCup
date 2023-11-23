package com.example.myapplication;

public class MenuInfo {
    private String menuName;
    private String imagePath;

    public MenuInfo(String menuName, String imagePath) {
        this.menuName = menuName;
        this.imagePath = imagePath;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getImagePath() {
        return imagePath;
    }
}
