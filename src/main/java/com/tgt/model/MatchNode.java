package com.tgt.model;


public class MatchNode implements Comparable<MatchNode> {
    private String value;
    private Double score;
    private int substring_start_index;
    private int fuzzy;

    public MatchNode() {
    }

    public MatchNode(String value, int substring_start_index) {
        this.value = value;
        this.substring_start_index = substring_start_index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getSubstring_start_index() {
        return substring_start_index;
    }

    public void setSubstring_start_index(int substring_start_index) {
        this.substring_start_index = substring_start_index;
    }

    public int getFuzzy() {
        return fuzzy;
    }

    public void setFuzzy(int fuzzy) {
        this.fuzzy = fuzzy;
    }

    @Override
    public int compareTo(MatchNode node) {
        if(this.getScore() > node.getScore()) return -1;
        if(this.getScore() < node.getScore()) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "MatchNode{" +
                "value='" + value + '\'' +
                ", score=" + score +
                ", substring_start_index=" + substring_start_index +
                ", fuzzy=" + fuzzy +
                '}';
    }
}
