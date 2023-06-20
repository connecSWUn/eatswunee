package com.example.eatswunee.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class orderMenuList {

    @SerializedName("menuId")
    private int menuId;
    @SerializedName("menuCnt")
    private int menuCnt;

    public orderMenuList(int menuId, int menuCnt) {
        this.menuId = menuId;
        this.menuCnt = menuCnt;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getMenuCnt() {
        return menuCnt;
    }

    public void setMenuCnt(int menuCnt) {
        this.menuCnt = menuCnt;
    }
}
