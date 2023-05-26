package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class writer {

    @SerializedName("user_id")
    @Expose
    private long user_id;
    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("user_profile_url")
    @Expose
    private String user_profile_url;


    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_profile_url() {
        return user_profile_url;
    }

    public void setUser_profile_url(String user_profile_url) {
        this.user_profile_url = user_profile_url;
    }
}
