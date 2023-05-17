package com.example.eatswunee.server;

import com.google.gson.annotations.SerializedName;

public class CommunityResponse {
    @SerializedName("data")
    private post post;

    public com.example.eatswunee.server.post getPost() {
        return post;
    }

    public void setPost(com.example.eatswunee.server.post post) {
        this.post = post;
    }
}
