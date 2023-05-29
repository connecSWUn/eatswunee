package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("post")
    @Expose
    private Post post;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }

}
