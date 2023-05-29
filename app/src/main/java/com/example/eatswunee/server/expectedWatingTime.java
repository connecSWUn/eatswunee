package com.example.eatswunee.server;

import com.google.gson.annotations.SerializedName;

public class expectedWatingTime {

    @SerializedName("restaurantId")
    private int restaurantId;
    @SerializedName("expectedWatingTime")
    private int expectedWatingTime;


    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getExpectedWatingTime() {
        return expectedWatingTime;
    }

    public void setExpectedWatingTime(int expectedWatingTime) {
        this.expectedWatingTime = expectedWatingTime;
    }
}
