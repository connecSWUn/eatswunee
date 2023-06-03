package com.example.eatswunee.community;

import com.google.gson.annotations.SerializedName;

public class article {

    @SerializedName("title")
    private String title;
    @SerializedName("recruitStatus")
    private String recruitStatus;
    @SerializedName("start_time")
    private String start_time;
    @SerializedName("end_time")
    private String end_time;
    @SerializedName("recruit_spot")
    private String recruit_spot;
    @SerializedName("content")
    private String content;

    public article(String title, String recruitStatus, String start_time, String end_time, String recruit_spot, String content) {
        this.title = title;
        this.recruitStatus = recruitStatus;
        this.start_time = start_time;
        this.end_time = end_time;
        this.recruit_spot = recruit_spot;
        this.content = content;
    }
}
