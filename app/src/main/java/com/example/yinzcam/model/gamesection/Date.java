package com.example.yinzcam.model.gamesection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("Text")
    @Expose
    private String text;

    @SerializedName("Numeric")
    @Expose
    private String numeric;

    public String getNumeric() {
        return numeric;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Date{" +
                "text='" + text + '\'' +
                ", numeric='" + numeric + '\'' +
                '}';
    }
}
