package com.example.yinzcam.model.gamesection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("Opponent")
    @Expose
    private Opponent opponent;

    @SerializedName("GameState")
    @Expose
    private String gameState;

    @SerializedName("AwayScore")
    @Expose
    private String awayScore;

    @SerializedName("HomeScore")
    @Expose
    private String homeScore;

    @SerializedName("Date")
    @Expose
    private Date date;

    @SerializedName("Week")
    @Expose
    private String week;

    @SerializedName("Result")
    @Expose
    private String result;

    @SerializedName("Home")
    @Expose
    private String home;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    @Override
    public String toString() {
        return "Game{" +
                "opponent=" + opponent +
                ", gameState='" + gameState + '\'' +
                ", awayScore='" + awayScore + '\'' +
                ", homeScore='" + homeScore + '\'' +
                ", date=" + date +
                ", week='" + week + '\'' +
                ", result='" + result + '\'' +
                ", home='" + home + '\'' +
                '}';
    }

}
