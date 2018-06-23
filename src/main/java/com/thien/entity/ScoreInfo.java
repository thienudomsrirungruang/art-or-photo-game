package com.thien.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="score")
public class ScoreInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="game_id")
    private int gameId;
    @Column(name="user_id")
    private int userId;
    @Column(name="score")
    private int score;
    @Column(name="score_date")
    private Date scoreDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(Date scoreDate) {
        this.scoreDate = scoreDate;
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
