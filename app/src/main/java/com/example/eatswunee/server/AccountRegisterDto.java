package com.example.eatswunee.server;

import com.google.gson.annotations.SerializedName;

public class AccountRegisterDto {

    @SerializedName("loginId")
    private String loginId;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("password")
    private String password;

    public AccountRegisterDto (String loginId, String nickname, String password) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
    }
}
