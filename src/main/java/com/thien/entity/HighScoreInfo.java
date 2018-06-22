package com.thien.entity;

import javax.persistence.Entity;

@Entity
public class HighScoreInfo {
    int score;
    boolean has_score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isHas_score() {
        return has_score;
    }

    public void setHas_score(boolean has_score) {
        this.has_score = has_score;
    }
}
