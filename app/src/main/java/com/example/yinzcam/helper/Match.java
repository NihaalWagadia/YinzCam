package com.example.yinzcam.helper;

public class Match {

    String awayTeamName, homeScore, awayScore, time, week, gameState, imageUrl, homeImageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHomeImageUrl() {
        return homeImageUrl;
    }

    public void setHomeImageUrl(String homeImageUrl) {
        this.homeImageUrl = homeImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public Match(String awayTeamName, String homeScore, String awayScore, String time, String week, String gameState, String imageUrl, String homeImageUrl) {
        this.awayTeamName = awayTeamName;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.time = time;
        this.week = week;
        this.gameState = gameState;
        this.imageUrl = imageUrl;
        this.homeImageUrl = homeImageUrl;
    }

}
