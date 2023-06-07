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


    /* 마이페이지 주문 목록 조회 */
    @SerializedName("order_created_at")
    private String order_created_at;
    @SerializedName("order_menu_id")
    private long order_menu_id;
    @SerializedName("restaurant_name")
    private String restaurant_name;
    @SerializedName("is_user_write_review")
    private Boolean is_user_write_review;

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(int menu_price) {
        this.menu_price = menu_price;
    }

    public int getMenu_cnt() {
        return menu_cnt;
    }

    public void setMenu_cnt(int menu_cnt) {
        this.menu_cnt = menu_cnt;
    }

    public int getMenu_total_price() {
        return menu_total_price;
    }

    public void setMenu_total_price(int menu_total_price) {
        this.menu_total_price = menu_total_price;
    }

    public String getOrder_created_at() {
        return order_created_at;
    }

    public void setOrder_created_at(String order_created_at) {
        this.order_created_at = order_created_at;
    }

    public long getOrder_menu_id() {
        return order_menu_id;
    }

    public void setOrder_menu_id(long order_menu_id) {
        this.order_menu_id = order_menu_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public Boolean getIs_user_write_review() {
        return is_user_write_review;
    }

    public void setIs_user_write_review(Boolean is_user_write_review) {
        this.is_user_write_review = is_user_write_review;
    }
}
