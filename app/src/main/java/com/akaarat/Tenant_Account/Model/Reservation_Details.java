package com.akaarat.Tenant_Account.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reservation_Details {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("unitId")
    @Expose
    private Integer unitId;
    @SerializedName("bookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("rentDate")
    @Expose
    private String rentDate;
    @SerializedName("tenantId")
    @Expose
    private Integer tenantId;
    @SerializedName("renttime")
    @Expose
    private String renttime;
    @SerializedName("paymrntmethod")
    @Expose
    private String paymrntmethod;
    @SerializedName("customername")
    @Expose
    private String customername;
    @SerializedName("unitname")
    @Expose
    private String unitname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getRenttime() {
        return renttime;
    }

    public void setRenttime(String renttime) {
        this.renttime = renttime;
    }

    public String getPaymrntmethod() {
        return paymrntmethod;
    }

    public void setPaymrntmethod(String paymrntmethod) {
        this.paymrntmethod = paymrntmethod;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
}
