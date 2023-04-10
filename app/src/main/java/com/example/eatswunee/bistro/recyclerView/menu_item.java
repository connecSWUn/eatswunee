package com.example.eatswunee.bistro.recyclerView;

public class menu_item {

    int resourceId;
    String bistro_name;
    String star_rate;
    String menu_name;
    String price;

    public menu_item(int resourceId, String bistro_name, String star_rate, String menu_name, String price) {
        this.resourceId = resourceId;
        this.bistro_name = bistro_name;
        this.star_rate = star_rate;
        this.menu_name = menu_name;
        this.price = price;
    }

    public int getResourceId() { return resourceId; }

    public String getBistro_name() {
        return bistro_name;
    }

    public String getStar_rate() {
        return star_rate;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public String getPrice() {
        return price;
    }

    public void setResourceId() { this.resourceId = resourceId; }

    public void setBistro_name(String bistro_name) {
        this.bistro_name = bistro_name;
    }

    public void setStar_rate(String star_rate) {
        this.star_rate = star_rate;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
