package com.example.eatswunee.bistro.recyclerView;

public class reviewItem {
    int resourceId;
    String name;
    String message;
    String date;
    int star_rate;


    public reviewItem(int resourceId, String name, String message, String date, int star_rate) {
        this.resourceId = resourceId;
        this.name = name;
        this.message = message;
        this.date = date;
        this.star_rate = star_rate;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStar_rate() {
        return star_rate;
    }

    public void setStar_rate(int star_rate) {
        this.star_rate = star_rate;
    }
}
