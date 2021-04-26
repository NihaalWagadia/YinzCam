package com.example.yinzcam.model.responseobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamInfo {

    @SerializedName("TriCode")
    @Expose
    private String homeTriCode;

    @SerializedName("Name")
    @Expose
    private String homeName;

    public String getHomeTriCode() {
        return homeTriCode;
    }

    public void setHomeTriCode(String homeTriCode) {
        this.homeTriCode = homeTriCode;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    @Override
    public String toString() {
        return "TeamInfo{" +
                "homeTriCode='" + homeTriCode + '\'' +
                ", homeName='" + homeName + '\'' +
                '}';
    }
}
