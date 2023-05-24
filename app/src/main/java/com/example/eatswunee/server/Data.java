package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Data {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("cursorId")
    @Expose
    private String cursorId;
    @SerializedName("post")
    @Expose
    private List<Post> postList = null;


    @SerializedName("post_id")
    private long post_id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("title")
    private String title;
    @SerializedName("spot")
    private String spot;
    @SerializedName("start_time")
    private String start_time;
    @SerializedName("end_time")
    private String end_time;
    @SerializedName("recruit_status")
    private String recruit_status;
    @SerializedName("writer")
    private writer writers = null;
    @SerializedName("content")
    private String content;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCursorId() {
        return cursorId;
    }

    public void setCursorId(String cursorId) {
        this.cursorId = cursorId;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }


    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRecruit_status() {
        return recruit_status;
    }

    public void setRecruit_status(String recruit_status) {
        this.recruit_status = recruit_status;
    }

    public writer getWriters() {
        return writers;
    }

    public void setWriters(writer writers) {
        this.writers = writers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
