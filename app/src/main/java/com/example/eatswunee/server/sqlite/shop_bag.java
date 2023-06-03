package com.example.eatswunee.server.sqlite;

public class shop_bag {

    private long menu_id;
    private int menu_price;
    private int menu_cnt;
    private String menu_name;
    private String menu_image;
    private String restaurant_name;

    public shop_bag(long menu_id, String menu_name, String menu_image, int menu_price, String restaurant_name, int menu_cnt){
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_image = menu_image;
        this.menu_price = menu_price;
        this.restaurant_name = restaurant_name;
        this.menu_cnt = menu_cnt;
    }

    public long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(long menu_id) {
        this.menu_id = menu_id;
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

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_image() {
        return menu_image;
    }

    public void setMenu_image(String menu_image) {
        this.menu_image = menu_image;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
}
