package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class homeOrders {

    @SerializedName("orderId")
    @Expose
    private long orderId;
    @SerializedName("orderNum")
    @Expose
    private int orderNum;
    @SerializedName("restaurantId")
    @Expose
    private int restaurantId;
    @SerializedName("expectedWaitingTime")
    @Expose
    private int expectedWaitingTime;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getExpectedWaitingTime() {
        return expectedWaitingTime;
    }

    public void setExpectedWaitingTime(int expectedWaitingTime) {
        this.expectedWaitingTime = expectedWaitingTime;
    }
}
