package com.tw.cricketScorer.game.score;

import com.tw.cricketScorer.game.Batsman;

import java.util.List;
import java.util.UUID;

public class Score {


    private int score;
    private int over;
    private UUID gameId;

    private List<Batsman> currentBatsmen;

    public Score() {
    }

    public Score(List<Batsman> curentBatsmen, int score, int over, UUID gameId) {
        this.currentBatsmen = curentBatsmen;
        this.score = score;
        this.over = over;
        this.gameId = gameId;
    }



    public void setScore(int score) {
        this.score = score;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public Integer getScore() {
        return this.score;
    }

    public Integer getOver() {
        return this.over;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getGameId() {
        return gameId;
    }

    public UUID getBatsmanOnStrike() {
        return currentBatsmen.stream().filter(batsman -> batsman.getOnStrike()).findFirst().get().getId();
    }

    public void setCurrentBatsmen(List<Batsman> currentBatsmen) {
        this.currentBatsmen = currentBatsmen;
    }
}
