package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class reviewRating {

    @SerializedName("score5Cnt")
    @Expose
    private long score5Cnt;
    @SerializedName("score4Cnt")
    @Expose
    private long score4Cnt;
    @SerializedName("score3Cnt")
    @Expose
    private long score3Cnt;
    @SerializedName("score2Cnt")
    @Expose
    private long score2Cnt;
    @SerializedName("score1Cnt")
    @Expose
    private long score1Cnt;

    public long getScore5Cnt() {
        return score5Cnt;
    }

    public void setScore5Cnt(long score5Cnt) {
        this.score5Cnt = score5Cnt;
    }

    public long getScore4Cnt() {
        return score4Cnt;
    }

    public void setScore4Cnt(long score4Cnt) {
        this.score4Cnt = score4Cnt;
    }

    public long getScore3Cnt() {
        return score3Cnt;
    }

    public void setScore3Cnt(long score3Cnt) {
        this.score3Cnt = score3Cnt;
    }

    public long getScore2Cnt() {
        return score2Cnt;
    }

    public void setScore2Cnt(long score2Cnt) {
        this.score2Cnt = score2Cnt;
    }

    public long getScore1Cnt() {
        return score1Cnt;
    }

    public void setScore1Cnt(long score1Cnt) {
        this.score1Cnt = score1Cnt;
    }
}
