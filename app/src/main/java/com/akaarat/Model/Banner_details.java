package com.akaarat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banner_details {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("videoSrc")
    @Expose
    private Object videoSrc;
    @SerializedName("clientId")
    @Expose
    private Object clientId;
    @SerializedName("fileType")
    @Expose
    private Integer fileType;
    @SerializedName("defaultimg")
    @Expose
    private Object defaultimg;
    @SerializedName("unitId")
    @Expose
    private Integer unitId;
    @SerializedName("messageid")
    @Expose
    private Object messageid;
    @SerializedName("client")
    @Expose
    private Object client;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("unit")
    @Expose
    private Object unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Object getVideoSrc() {
        return videoSrc;
    }

    public void setVideoSrc(Object videoSrc) {
        this.videoSrc = videoSrc;
    }

    public Object getClientId() {
        return clientId;
    }

    public void setClientId(Object clientId) {
        this.clientId = clientId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Object getDefaultimg() {
        return defaultimg;
    }

    public void setDefaultimg(Object defaultimg) {
        this.defaultimg = defaultimg;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Object getMessageid() {
        return messageid;
    }

    public void setMessageid(Object messageid) {
        this.messageid = messageid;
    }

    public Object getClient() {
        return client;
    }

    public void setClient(Object client) {
        this.client = client;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getUnit() {
        return unit;
    }

    public void setUnit(Object unit) {
        this.unit = unit;
    }

}
