package com.tw.cricketScorer.game.score;

import com.tw.cricketScorer.game.Batsman;

import java.util.List;
import java.util.UUID;

public class Score {


    private int score;
    private int overNumber;
    private UUID gameId;

    private List<Batsman> curentBatsmen;

    public Score() {
    }

    public Score(List<Batsman> curentBatsmen, int score, int overNumber, UUID gameId) {
        this.curentBatsmen = curentBatsmen;
        this.score = score;
        this.overNumber = overNumber;
        this.gameId = gameId;
    }



    public void setScore(int score) {
        this.score = score;
    }

    public void setOverNumber(int overNumber) {
        this.overNumber = overNumber;
    }

    public Integer getScore() {
        return this.score;
    }

    public Integer getOverNumber() {
        return this.overNumber;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public UUID getBatsmanOnStrike() {
        return curentBatsmen.stream().filter(batsman -> batsman.getOnStrike()).findFirst().get().getId();
    }

    public void setCurentBatsmen(List<Batsman> curentBatsmen) {
        this.curentBatsmen = curentBatsmen;
    }
}
