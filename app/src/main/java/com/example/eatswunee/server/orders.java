package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class orders {

    @SerializedName("orderId")
    @Expose
    private int orderId;
    @SerializedName("orderNum")
    @Expose
    private long orderNum;
    @SerializedName("expectedWatingTime")
    private expectedWatingTime expectedWatingTime = null;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }

    public expectedWatingTime getExpectedWatingTime() {
        return expectedWatingTime;
    }

    public void setExpectedWatingTime(expectedWatingTime expectedWatingTime) {
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
}
