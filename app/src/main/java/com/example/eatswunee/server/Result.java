package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("post")
    @Expose
    private List<Post> postList;

    /* 게시글 등록 API */
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("recruitStatus")
    @Expose
    private String recruitStatus;
    @SerializedName("start_time")
    @Expose
    private String start_time;
    @SerializedName("end_time")
    @Expose
    private String end_time;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("writer_id")
    @Expose
    private long writer_id;


    /* 작성 글 조회 API */
    @SerializedName("post_total_cnt")
    @Expose
    private int post_total_cnt;

    public int getPost_total_cnt() {
        return post_total_cnt;
    }

    public void setPost_total_cnt(int post_total_cnt) {
        this.post_total_cnt = post_total_cnt;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    /* 게시글 등록 API */

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(long writer_id) {
        this.writer_id = writer_id;
    }
}
