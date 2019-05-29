package com.akaarat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dynamic_Attributes {

    @SerializedName("inputvalue")
    @Expose
    private String inputvalue;
    @SerializedName("labelname")
    @Expose
    private String labelname;
    @SerializedName("measruingunittext")
    @Expose
    private String measruingunittext;

    public String getInputvalue() {
        return inputvalue;
    }

    public void setInputvalue(String inputvalue) {
        this.inputvalue = inputvalue;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getMeasruingunittext() {
        return measruingunittext;
    }

    public void setMeasruingunittext(String measruingunittext) {
        this.measruingunittext = measruingunittext;
    }
}
