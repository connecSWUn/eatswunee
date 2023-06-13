package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class posts {

    @SerializedName("postId")
    @Expose
    private long postId;
    @SerializedName("postTitle")
    @Expose
    private String postTitle;
    @SerializedName("postStartTime")
    @Expose
    private String postStartTime;
    @SerializedName("postEndTime")
    @Expose
    private String postEndTime;
    @SerializedName("postCreatedAt")
    @Expose
    private String postCreatedAt;
    @SerializedName("postRecruitStatus")
    @Expose
    private String postRecruitStatus;
    @SerializedName("postSpot")
    @Expose
    private String postSpot;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostStartTime() {
        return postStartTime;
    }

    public void setPostStartTime(String postStartTime) {
        this.postStartTime = postStartTime;
    }

    public String getPostEndTime() {
        return postEndTime;
    }

    public void setPostEndTime(String postEndTime) {
        this.postEndTime = postEndTime;
    }

    public String getPostCreatedAt() {
        return postCreatedAt;
    }

    public void setPostCreatedAt(String postCreatedAt) {
        this.postCreatedAt = postCreatedAt;
    }

    public String getPostRecruitStatus() {
        return postRecruitStatus;
    }

    public void setPostRecruitStatus(String postRecruitStatus) {
        this.postRecruitStatus = postRecruitStatus;
    }

    public String getPostSpot() {
        return postSpot;
    }

    public void setPostSpot(String postSpot) {
        this.postSpot = postSpot;
    }
}
