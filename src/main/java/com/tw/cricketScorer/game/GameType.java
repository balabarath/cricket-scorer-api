package com.tw.cricketScorer.game;

public enum GameType {
    TwentyTwenty("2020"),
    ODI("ODI"),
    TEST("TEST");

    private String dbType;

    GameType(String dbType) {
        this.dbType = dbType;
    }

    public String dbType() {
        return this.dbType;
    }
}
