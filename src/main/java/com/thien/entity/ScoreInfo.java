package com.thien.entity;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class ScoreInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="game_id")
    private int game_id;
    @Column(name="user_id")
    private int user_id;
    @Column(name="score")
    private int score;
    @Column(name="score_date")
    private Date score_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getScore_date() {
        return score_date;
    }

    public void setScore_date(Date score_date) {
        this.score_date = score_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreInfo scoreInfo = (ScoreInfo) o;
        return id == scoreInfo.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
