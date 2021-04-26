package com.example.yinzcam.model;

import com.example.yinzcam.model.responseobjects.GameSection;
import com.example.yinzcam.model.responseobjects.TeamInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleApiResponse {

    public  ArrayList<GameSection> getGameSections(){
        return gameSections;
    }

    public void setGameSections(ArrayList<GameSection> gameSections){
        this.gameSections = gameSections;
    }

    @SerializedName("GameSection")
    @Expose
    private ArrayList<GameSection> gameSections;

    @SerializedName("Team")
    @Expose
    private TeamInfo team;

    public TeamInfo getTeam() {
        return team;
    }

    public void setTeam(TeamInfo team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "ScheduleApiResponse{" +
                "gameSections=" + gameSections +
                ", team=" + team +
                '}';
    }
}
