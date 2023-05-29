package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class searchedMenus {

    @SerializedName("menuId")
    @Expose
    private long menuId;
    @SerializedName("menuName")
    @Expose
    private String menuName;
    @SerializedName("menuRating")
    @Expose
    private float menuRating;
    @SerializedName("menuPrice")
    @Expose
    private int menuPrice;
    @SerializedName("RestaurantName")
    @Expose
    private String RestaurantName;


    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public float getMenuRating() {
        return menuRating;
    }

    public void setMenuRating(float menuRating) {
        this.menuRating = menuRating;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }
}
