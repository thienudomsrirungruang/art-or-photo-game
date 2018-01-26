package com.thien.entity;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonView;

@JsonAutoDetect
public class ScoreInfo {

    @JsonView
    private boolean hasScore;
    @JsonView
    private int score;

    public boolean hasScore() {
        return hasScore;
    }

    public void setHasScore(boolean hasScore) {
        this.hasScore = hasScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
