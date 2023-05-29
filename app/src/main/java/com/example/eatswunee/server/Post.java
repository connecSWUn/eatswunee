package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("recruitId")
    @Expose
    private Long recruitId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("recruitStatus")
    @Expose
    private String recruitStatus;
    @SerializedName("spot")
    @Expose
    private String spot;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;

    /* 작성 게시물 조회 */
    @SerializedName("post_title")
    @Expose
    private String post_title;
    @SerializedName("post_spot")
    @Expose
    private String post_spot;
    @SerializedName("post_start_time")
    @Expose
    private String post_start_time;
    @SerializedName("post_end_time")
    @Expose
    private String post_end_time;
    @SerializedName("post_created_at")
    @Expose
    private String post_created_at;

    public Long getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Long recruitId) {
        this.recruitId = recruitId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecruitStatus() {
        return recruitStatus;
    }

    public void setRecruitStatus(String recruitStatus) {
        this.recruitStatus = recruitStatus;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /* 작성 글 조회 */

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_spot() {
        return post_spot;
    }

    public void setPost_spot(String post_spot) {
        this.post_spot = post_spot;
    }

    public String getPost_start_time() {
        return post_start_time;
    }

    public void setPost_start_time(String post_start_time) {
        this.post_start_time = post_start_time;
    }

    public String getPost_end_time() {
        return post_end_time;
    }

    public void setPost_end_time(String post_end_time) {
        this.post_end_time = post_end_time;
    }

    public String getPost_created_at() {
        return post_created_at;
    }

    public void setPost_created_at(String post_created_at) {
        this.post_created_at = post_created_at;
    }
}
