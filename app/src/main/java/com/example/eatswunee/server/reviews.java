package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class reviews {

    @SerializedName("reviewId")
    @Expose
    private long reviewId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("reviewContent")
    @Expose
    private String reviewContent;
    @SerializedName("menuRating")
    @Expose
    private int menuRating;
    @SerializedName("reviewImgs")
    private ArrayList<String> reviewImgsList;
    @SerializedName("writer")
    private List<writer> writerList;


    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public int getMenuRating() {
        return menuRating;
    }

    public void setMenuRating(int menuRating) {
        this.menuRating = menuRating;
    }

    public ArrayList<String> getReviewImgsList() {
        return reviewImgsList;
    }

    public void setReviewImgsList(ArrayList<String> reviewImgsList) {
        this.reviewImgsList = reviewImgsList;
    }

    public List<writer> getWriterList() {
        return writerList;
    }

    public void setWriterList(List<writer> writerList) {
        this.writerList = writerList;
    }
}
