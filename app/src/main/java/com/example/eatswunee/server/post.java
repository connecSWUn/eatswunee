package com.example.eatswunee.server;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class post {
    @SerializedName("post")
    private List<CommunityDto> post = new ArrayList<CommunityDto>();

    public List<CommunityDto> getPost() {
        return post;
    }

    public void setPost(List<CommunityDto> post) {
        this.post = post;
    }
}
