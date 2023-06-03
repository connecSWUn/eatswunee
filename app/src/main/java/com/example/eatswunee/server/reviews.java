package com.example.eatswunee.server;

import android.widget.ImageView;

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
    @SerializedName("reveiewContent")
    @Expose
    private String reveiewContent;
    @SerializedName("menuRating")
    @Expose
    private int menuRating;
    @SerializedName("reviewImgs")
    private ArrayList<String> reviewImgsList;
    @SerializedName("writer")
    private writer writer;

    /* 내가 작성한 리뷰 조회 */
    @SerializedName("restaurant_name")
    @Expose
    private String restaurant_name;
    @SerializedName("menu_name")
    @Expose
    private String menu_name;
    @SerializedName("review_image_url")
    @Expose
    private String review_image_url;
    @SerializedName("menu_review_rating")
    @Expose
    private double menu_review_rating;
    @SerializedName("review_content")
    @Expose
    private String review_content;
    @SerializedName("review_created_at")
    @Expose
    private String review_created_at;


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
        return reveiewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reveiewContent = reveiewContent;
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

    public com.example.eatswunee.server.writer getWriter() {
        return writer;
    }

    public void setWriter(com.example.eatswunee.server.writer writer) {
        this.writer = writer;
    }

    /* 내 리뷰 보기 */

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getReview_image_url() {
        return review_image_url;
    }

    public void setReview_image_url(String review_image_url) {
        this.review_image_url = review_image_url;
    }

    public double getMenu_review_rating() {
        return menu_review_rating;
    }

    public void setMenu_review_rating(double menu_review_rating) {
        this.menu_review_rating = menu_review_rating;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public String getReview_created_at() {
        return review_created_at;
    }

    public void setReview_created_at(String review_created_at) {
        this.review_created_at = review_created_at;
    }
}
