package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class orders {

    /* 마이페이지 주문 목록 조회 */
    @SerializedName("orderMenuId")
    private long orderMenuId;
    @SerializedName("orderCreatedAt")
    private String orderCreatedAt;
    @SerializedName("restaurantName")
    private String restaurantName;
    @SerializedName("menuName")
    private String menuName;
    @SerializedName("menuTotalPrice")
    private int menuTotalPrice;
    @SerializedName("menuPrice")
    private int menuPrice;
    @SerializedName("menuCnt")
    private int menuCnt;
    @SerializedName("userWriteReview")
    private boolean userWriteReview;

    public long getOrderMenuId() {
        return orderMenuId;
    }

    public void setOrderMenuId(long orderMenuId) {
        this.orderMenuId = orderMenuId;
    }

    public String getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(String orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuTotalPrice() {
        return menuTotalPrice;
    }

    public void setMenuTotalPrice(int menuTotalPrice) {
        this.menuTotalPrice = menuTotalPrice;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getMenuCnt() {
        return menuCnt;
    }

    public void setMenuCnt(int menuCnt) {
        this.menuCnt = menuCnt;
    }

    public boolean isUserWriteReview() {
        return userWriteReview;
    }

    public void setUserWriteReview(boolean userWriteReview) {
        this.userWriteReview = userWriteReview;
    }
}
