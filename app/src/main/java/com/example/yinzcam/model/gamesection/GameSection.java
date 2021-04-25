package com.example.yinzcam.model.gamesection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GameSection {

    @SerializedName("Game")
    @Expose
    private ArrayList<Game> game;

    @Override
    public String toString() {
        return "GameSection{" +
                "game=" + game +
                '}';
    }

    public ArrayList<Game> getGame() {
        return game;
    }

    public void setGame(ArrayList<Game> game) {
        this.game = game;
    }


}
