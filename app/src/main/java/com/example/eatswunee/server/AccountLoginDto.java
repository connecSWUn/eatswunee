package com.example.eatswunee.server;

import com.google.gson.annotations.SerializedName;

public class AccountLoginDto {

    @SerializedName("loginId")
    private String loginId;
    @SerializedName("password")
    private String password;

    public AccountLoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
