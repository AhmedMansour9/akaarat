package com.akaarat.Tenant_Account.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fromUser")
    @Expose
    private String fromUser;
    @SerializedName("fromUserId")
    @Expose
    private Integer fromUserId;
    @SerializedName("touser")
    @Expose
    private String touser;
    @SerializedName("touserId")
    @Expose
    private Integer touserId;
    @SerializedName("messageBody")
    @Expose
    private String messageBody;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public Integer getTouserId() {
        return touserId;
    }

    public void setTouserId(Integer touserId) {
        this.touserId = touserId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
