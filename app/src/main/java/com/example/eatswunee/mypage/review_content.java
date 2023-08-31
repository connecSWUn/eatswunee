package com.example.eatswunee.mypage;

import com.google.gson.annotations.SerializedName;

public class review_content {

    @SerializedName("order_menu_id")
    private long order_menu_id;
    @SerializedName("menu_review_rating")
    private double menu_review_rating;
    @SerializedName("review_image_url")
    private String review_image_url;
    @SerializedName("review_content")
    private String review_content;

    public review_content(long order_menu_id, double menu_review_rating, String review_image_url, String review_content) {
        this.order_menu_id = order_menu_id;
        this.menu_review_rating = menu_review_rating;
        this.review_image_url = review_image_url;
        this.review_content = review_content;
    }
}
