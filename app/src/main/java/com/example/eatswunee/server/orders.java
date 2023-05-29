package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
}
