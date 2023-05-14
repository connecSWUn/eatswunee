package com.example.eatswunee.server;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Result {
    @SerializedName("recruitId")
    private int recruitId;
    @SerializedName("createdAt")
    private LocalDateTime createdAt;
    @SerializedName("title")
    private String title;
    @SerializedName("status")
    private String status;
    @SerializedName("spot")
    private String spot;
    @SerializedName("startTime")
    private LocalDateTime startTime;
    @SerializedName("endTime")
    private LocalDateTime endTime;

    public int getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(int recruitId) {
        this.recruitId = recruitId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
