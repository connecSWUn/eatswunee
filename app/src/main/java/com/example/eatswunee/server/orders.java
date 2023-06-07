package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class orders {

    /* 식당별 메뉴 리스트 조회 */
    @SerializedName("orderId")
    @Expose
    private int orderId;
    @SerializedName("orderNum")
    @Expose
    private int orderNum;
    @SerializedName("restaurantId")
    @Expose
    private long restaurantId;
    @SerializedName("expectedWatingTime")
    private int expectedWatingTime;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getExpectedWatingTime() {
        return expectedWatingTime;
    }

    public void setExpectedWatingTime(int expectedWatingTime) {
        this.expectedWatingTime = expectedWatingTime;
    }

    /* 주문 내역 조회 : 주문 */
    @SerializedName("restaurant_name")
    @Expose
    private String restaurant_name;
    @SerializedName("order_restaurant_waiting_time")
    @Expose
    private int order_restaurant_waiting_time;
    @SerializedName("restaurant_total_price")
    @Expose
    private int restaurant_total_price;
    @SerializedName("menus")
    private List<menus> menusList;

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public int getOrder_restaurant_waiting_time() {
        return order_restaurant_waiting_time;
    }

    public void setOrder_restaurant_waiting_time(int order_restaurant_waiting_time) {
        this.order_restaurant_waiting_time = order_restaurant_waiting_time;
    }

    public int getRestaurant_total_price() {
        return restaurant_total_price;
    }

    public void setRestaurant_total_price(int restaurant_total_price) {
        this.restaurant_total_price = restaurant_total_price;
    }

    public List<menus> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<menus> menusList) {
        this.menusList = menusList;
    }


    /* 마이페이지 주문 목록 조회 */
    @SerializedName("order_id")
    private long order_id;
    @SerializedName("order_created_at")
    private String order_created_at;
    @SerializedName("order_total_price")
    private String order_total_price;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getOrder_created_at() {
        return order_created_at;
    }

    public void setOrder_created_at(String order_created_at) {
        this.order_created_at = order_created_at;
    }

    public String getOrder_total_price() {
        return order_total_price;
    }

    public void setOrder_total_price(String order_total_price) {
        this.order_total_price = order_total_price;
    }
}
