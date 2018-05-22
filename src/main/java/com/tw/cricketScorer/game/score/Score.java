package com.tw.cricketScorer.game.score;

import java.util.UUID;

public class Score {

    private UUID playerId;
    private int score;
    private int overNumber;
    private UUID gameId;

    public Score() {
    }

    public Score(UUID playerId, int score, int overNumber, UUID gameId) {
        this.playerId = playerId;
        this.score = score;
        this.overNumber = overNumber;
        this.gameId = gameId;
    }

    public void setPlayerId(UUID id) {
        this.playerId = id;
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

    public UUID getPlayerId() {
        return playerId;
    }
}
