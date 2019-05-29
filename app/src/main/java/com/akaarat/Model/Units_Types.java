package com.akaarat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Units_Types {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("namear")
    @Expose
    private String namear;
    @SerializedName("nameen")
    @Expose
    private String nameen;
    @SerializedName("addarea")
    @Expose
    private Object addarea;
    @SerializedName("addnoofrooms")
    @Expose
    private Object addnoofrooms;
    @SerializedName("addnomofbathrooms")
    @Expose
    private Object addnomofbathrooms;
    @SerializedName("addbuildingno")
    @Expose
    private Object addbuildingno;
    @SerializedName("addfloorno")
    @Expose
    private Object addfloorno;
    @SerializedName("addheight")
    @Expose
    private Object addheight;
    @SerializedName("addpropertycount")
    @Expose
    private Object addpropertycount;
    @SerializedName("adddimentionnorth")
    @Expose
    private Object adddimentionnorth;
    @SerializedName("adddimensioneast")
    @Expose
    private Object adddimensioneast;
    @SerializedName("adddimensionwest")
    @Expose
    private Object adddimensionwest;
    @SerializedName("adddimensionsouth")
    @Expose
    private Object adddimensionsouth;
    @SerializedName("addelectricitymeternumber")
    @Expose
    private Object addelectricitymeternumber;
    @SerializedName("addage")
    @Expose
    private Object addage;
    @SerializedName("addcountoffloors")
    @Expose
    private Object addcountoffloors;
    @SerializedName("unit")
    @Expose
    private List<Object> unit = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setAddarea(Object addarea) {
        this.addarea = addarea;
    }

    public Object getAddnoofrooms() {
        return addnoofrooms;
    }

    public void setAddnoofrooms(Object addnoofrooms) {
        this.addnoofrooms = addnoofrooms;
    }

    public Object getAddnomofbathrooms() {
        return addnomofbathrooms;
    }

    public void setAddnomofbathrooms(Object addnomofbathrooms) {
        this.addnomofbathrooms = addnomofbathrooms;
    }

    public Object getAddbuildingno() {
        return addbuildingno;
    }

    public void setAddbuildingno(Object addbuildingno) {
        this.addbuildingno = addbuildingno;
    }

    public Object getAddfloorno() {
        return addfloorno;
    }

    public void setAddfloorno(Object addfloorno) {
        this.addfloorno = addfloorno;
    }

    public Object getAddheight() {
        return addheight;
    }

    public void setAddheight(Object addheight) {
        this.addheight = addheight;
    }

    public Object getAddpropertycount() {
        return addpropertycount;
    }

    public void setAddpropertycount(Object addpropertycount) {
        this.addpropertycount = addpropertycount;
    }

    public Object getAdddimentionnorth() {
        return adddimentionnorth;
    }

    public void setAdddimentionnorth(Object adddimentionnorth) {
        this.adddimentionnorth = adddimentionnorth;
    }

    public Object getAdddimensioneast() {
        return adddimensioneast;
    }

    public void setAdddimensioneast(Object adddimensioneast) {
        this.adddimensioneast = adddimensioneast;
    }

    public Object getAdddimensionwest() {
        return adddimensionwest;
    }

    public void setAdddimensionwest(Object adddimensionwest) {
        this.adddimensionwest = adddimensionwest;
    }

    public Object getAdddimensionsouth() {
        return adddimensionsouth;
    }

    public void setAdddimensionsouth(Object adddimensionsouth) {
        this.adddimensionsouth = adddimensionsouth;
    }

    public Object getAddelectricitymeternumber() {
        return addelectricitymeternumber;
    }

    public void setAddelectricitymeternumber(Object addelectricitymeternumber) {
        this.addelectricitymeternumber = addelectricitymeternumber;
    }

    public Object getAddage() {
        return addage;
    }

    public void setAddage(Object addage) {
        this.addage = addage;
    }

    public Object getAddcountoffloors() {
        return addcountoffloors;
    }

    public void setAddcountoffloors(Object addcountoffloors) {
        this.addcountoffloors = addcountoffloors;
    }

    public List<Object> getUnit() {
        return unit;
    }

    public void setUnit(List<Object> unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return namear;
    }
}
