package com.example.yinzcam.model.responseobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Opponent {

    @SerializedName("TriCode")
    @Expose
    private String triCode;

    @SerializedName("Name")
    @Expose
    private String name;

    public String getTriCode() {
        return triCode;
    }

    public void setTriCode(String triCode) {
        this.triCode = triCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Opponent{" +
                "triCode='" + triCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
