package com.example.eatswunee.community;

import java.time.LocalDateTime;

public class community_item {

    String title;
    String place;
    String app_time;
    String post_date;
    String state;

    public community_item (String title, String place, String app_time, String post_date, String state) {
        this.title = title;
        this.place = place;
        this.app_time = app_time;
        this.post_date = post_date;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getApp_time() {
        return app_time;
    }

    public void setApp_time(String app_time) {
        this.app_time = app_time;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
