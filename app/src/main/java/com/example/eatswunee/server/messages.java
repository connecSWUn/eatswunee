package com.example.eatswunee.server;

import android.text.Editable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class messages {

    @SerializedName("message_created_at")
    @Expose
    private String message_created_at;
    @SerializedName("message_sender")
    @Expose
    private String message_sender;
    @SerializedName("message_content")
    @Expose
    private String message_content;
    @SerializedName("message_is_read")
    @Expose
    private boolean message_is_read;

    public messages(String message_created_at, String message_sender, String message_content, boolean message_is_read) {
        this.message_created_at = message_created_at;
        this.message_sender = message_sender;
        this.message_content = message_content;
        this.message_is_read = message_is_read;
    }

    public String getMessage_created_at() {
        return message_created_at;
    }

    public void setMessage_created_at(String message_created_at) {
        this.message_created_at = message_created_at;
    }

    public String getMessage_sender() {
        return message_sender;
    }

    public void setMessage_sender(String message_sender) {
        this.message_sender = message_sender;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public boolean isMessage_is_read() {
        return message_is_read;
    }

    public void setMessage_is_read(boolean message_is_read) {
        this.message_is_read = message_is_read;
    }
}
