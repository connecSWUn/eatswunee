package com.example.eatswunee.mypage;

public class list_item {

    String bistro_name;
    String menu_name;
    String price;
    String date;

    public list_item (String bistro_name, String menu_name, String price, String date) {
        this.bistro_name = bistro_name;
        this.menu_name = menu_name;
        this.price = price;
        this.date = date;
    }

    public String getBistro_name() {
        return bistro_name;
    }

    public void setBistro_name(String bistro_name) {
        this.bistro_name = bistro_name;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
