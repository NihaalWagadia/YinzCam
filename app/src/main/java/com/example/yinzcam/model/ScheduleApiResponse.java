package com.example.yinzcam.model;

import com.example.yinzcam.model.gamesection.GameSection;
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

    @Override
    public String toString(){
        return "Feed{" +
                "GameSection= " + gameSections +
                '}';
    }
}
