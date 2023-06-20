package com.example.eatswunee.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class menuListDto {

    @SerializedName("orderMenuList")
    private orderMenuList[] orderMenuLists;

    public menuListDto(orderMenuList[] orderMenuLists) {
        this.orderMenuLists = orderMenuLists;
    }

    public orderMenuList[] getOrderMenuLists() {
        return orderMenuLists;
    }
}
