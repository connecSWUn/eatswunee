package com.example.eatswunee.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class chatRooms {

    @SerializedName("chatRoom")
    @Expose
    private long chatRoom;
    @SerializedName("recruitTitle")
    @Expose
    private String recruitTitle;
    @SerializedName("senderNickname")
    @Expose
    private String senderNickname;
    @SerializedName("senderProfileImgUrl")
    @Expose
    private String senderProfileImgUrl;
    @SerializedName("lastChatCreatedAt")
    @Expose
    private String lastChatCreatedAt;
    @SerializedName("lastChatMessage")
    @Expose
    private String lastChatMessage;

    public long getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(long chatRoom) {
        this.chatRoom = chatRoom;
    }

    public String getRecruitTitle() {
        return recruitTitle;
    }

    public void setRecruitTitle(String recruitTitle) {
        this.recruitTitle = recruitTitle;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public String getSenderProfileImgUrl() {
        return senderProfileImgUrl;
    }

    public void setSenderProfileImgUrl(String senderProfileImgUrl) {
        this.senderProfileImgUrl = senderProfileImgUrl;
    }

    public String getLastChatCreatedAt() {
        return lastChatCreatedAt;
    }

    public void setLastChatCreatedAt(String lastChatCreatedAt) {
        this.lastChatCreatedAt = lastChatCreatedAt;
    }

    public String getLastChatMessage() {
        return lastChatMessage;
    }

    public void setLastChatMessage(String lastChatMessage) {
        this.lastChatMessage = lastChatMessage;
    }
}
