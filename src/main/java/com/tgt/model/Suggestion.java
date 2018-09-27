package com.tgt.model;

public class Suggestion {
    private String value;

    private double score;

    public Suggestion() {
    }

    public Suggestion(String value, double score) {
        this.value = value;
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
