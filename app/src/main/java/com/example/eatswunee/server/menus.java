package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class menus {

    @SerializedName("menuId")
    @Expose
    private long menuId;
    @SerializedName("restaurantName")
    @Expose
    private String restaurantName;
    @SerializedName("menuImg")
    @Expose
    private String menuImg;
    @SerializedName("menuName")
    @Expose
    private String menuName;
    @SerializedName("menuRating")
    @Expose
    private float menuRating;
    @SerializedName("menuPrice")
    @Expose
    private String menuPrice;

    /* 주문 내역 조회 : 메뉴 */
    @SerializedName("menu_name")
    @Expose
    private String menu_name;
    @SerializedName("menu_price")
    @Expose
    private int menu_price;
    @SerializedName("menu_cnt")
    @Expose
    private int menu_cnt;
    @SerializedName("menu_total_price")
    @Expose
    private int menu_total_price;


    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg;
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

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }
}
