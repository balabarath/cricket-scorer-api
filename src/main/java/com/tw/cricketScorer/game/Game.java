package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.records.GameRecord;

public class Game {
    private String team1;
    private String team2;

    public Game(GameRecord gameRecord) {
        this.team1 = gameRecord.getTeam1();
        this.team2 = gameRecord.getTeam2();
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }
}
