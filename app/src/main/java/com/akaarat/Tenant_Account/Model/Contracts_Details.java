package com.akaarat.Tenant_Account.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contracts_Details {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("rentMethodType")
    @Expose
    private String rentMethodType;
    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("startContractDate")
    @Expose
    private String startContractDate;
    @SerializedName("endContractDate")
    @Expose
    private String endContractDate;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("tenentId")
    @Expose
    private Integer tenentId;
    @SerializedName("paymentMethodType")
    @Expose
    private String paymentMethodType;
    @SerializedName("customername")
    @Expose
    private String customername;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRentMethodType() {
        return rentMethodType;
    }

    public void setRentMethodType(String rentMethodType) {
        this.rentMethodType = rentMethodType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartContractDate() {
        return startContractDate;
    }

    public void setStartContractDate(String startContractDate) {
        this.startContractDate = startContractDate;
    }

    public String getEndContractDate() {
        return endContractDate;
    }

    public void setEndContractDate(String endContractDate) {
        this.endContractDate = endContractDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTenentId() {
        return tenentId;
    }

    public void setTenentId(Integer tenentId) {
        this.tenentId = tenentId;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
