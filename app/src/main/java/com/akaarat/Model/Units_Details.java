package com.akaarat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Units_Details {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("namear")
    @Expose
    private String namear;
    @SerializedName("nameen")
    @Expose
    private String nameen;
    @SerializedName("addarea")
    @Expose
    private String addarea;
    @SerializedName("addnoofrooms")
    @Expose
    private String addnoofrooms;
    @SerializedName("addnomofbathrooms")
    @Expose
    private String addnomofbathrooms;
    @SerializedName("addbuildingno")
    @Expose
    private String addbuildingno;
    @SerializedName("addfloorno")
    @Expose
    private String addfloorno;
    @SerializedName("addheight")
    @Expose
    private String addheight;
    @SerializedName("addpropertycount")
    @Expose
    private String addpropertycount;
    @SerializedName("adddimentionnorth")
    @Expose
    private String adddimentionnorth;
    @SerializedName("adddimensioneast")
    @Expose
    private String adddimensioneast;
    @SerializedName("adddimensionwest")
    @Expose
    private String adddimensionwest;
    @SerializedName("adddimensionsouth")
    @Expose
    private String adddimensionsouth;
    @SerializedName("addelectricitymeternumber")
    @Expose
    private String addelectricitymeternumber;
    @SerializedName("addage")
    @Expose
    private String addage;
    @SerializedName("addcountoffloors")
    @Expose
    private String addcountoffloors;
    @SerializedName("unit")
    @Expose
    private List<Units_Detail> unit = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamear() {
        return namear;
    }

    public void setNamear(String namear) {
        this.namear = namear;
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }

    public Object getAddarea() {
        return addarea;
    }

    public void setAddarea(String addarea) {
        this.addarea = addarea;
    }

    public Object getAddnoofrooms() {
        return addnoofrooms;
    }

    public void setAddnoofrooms(String addnoofrooms) {
        this.addnoofrooms = addnoofrooms;
    }

    public Object getAddnomofbathrooms() {
        return addnomofbathrooms;
    }

    public void setAddnomofbathrooms(String addnomofbathrooms) {
        this.addnomofbathrooms = addnomofbathrooms;
    }

    public String getAddbuildingno() {
        return addbuildingno;
    }

    public void setAddbuildingno(String addbuildingno) {
        this.addbuildingno = addbuildingno;
    }

    public String getAddfloorno() {
        return addfloorno;
    }

    public void setAddfloorno(String addfloorno) {
        this.addfloorno = addfloorno;
    }

    public String getAddheight() {
        return addheight;
    }

    public void setAddheight(String addheight) {
        this.addheight = addheight;
    }

    public String getAddpropertycount() {
        return addpropertycount;
    }

    public void setAddpropertycount(String addpropertycount) {
        this.addpropertycount = addpropertycount;
    }

    public Object getAdddimentionnorth() {
        return adddimentionnorth;
    }

    public void setAdddimentionnorth(String adddimentionnorth) {
        this.adddimentionnorth = adddimentionnorth;
    }

    public Object getAdddimensioneast() {
        return adddimensioneast;
    }

    public void setAdddimensioneast(String adddimensioneast) {
        this.adddimensioneast = adddimensioneast;
    }

    public Object getAdddimensionwest() {
        return adddimensionwest;
    }

    public void setAdddimensionwest(String adddimensionwest) {
        this.adddimensionwest = adddimensionwest;
    }

    public Object getAdddimensionsouth() {
        return adddimensionsouth;
    }

    public void setAdddimensionsouth(String adddimensionsouth) {
        this.adddimensionsouth = adddimensionsouth;
    }

    public Object getAddelectricitymeternumber() {
        return addelectricitymeternumber;
    }

    public void setAddelectricitymeternumber(String addelectricitymeternumber) {
        this.addelectricitymeternumber = addelectricitymeternumber;
    }

    public Object getAddage() {
        return addage;
    }

    public void setAddage(String addage) {
        this.addage = addage;
    }

    public Object getAddcountoffloors() {
        return addcountoffloors;
    }

    public void setAddcountoffloors(String addcountoffloors) {
        this.addcountoffloors = addcountoffloors;
    }

    public List<Units_Detail> getUnit() {
        return unit;
    }

    public void setUnit(List<Units_Detail> unit) {
        this.unit = unit;
    }


}
