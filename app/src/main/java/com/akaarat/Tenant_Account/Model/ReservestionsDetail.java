package com.akaarat.Tenant_Account.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservestionsDetail {
    private Integer id;
    private Integer unitId;
    private String bookingDate;
    private String rentDate;
    private Integer tenantId;
    private String renttime;
    private String paymrntmethod;
    private String customername;
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
