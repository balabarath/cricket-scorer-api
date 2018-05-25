package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.records.BallRecord;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class BatsmanDetails {

    private String name;
    private int runs;
    private int balls;
    private int fours;
    private int sixes;
    private String strikeRate;
    private UUID batsmanId;

    public BatsmanDetails() {
    }

    public BatsmanDetails(UUID batsmanId,String name, int runs, int balls, int fours, int sixes, String strikeRate) {
        this.batsmanId = batsmanId;
        this.name = name;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
    }

    public void buildFromBallRecord(BallRecord ballRecord, String name) {
        this.batsmanId = ballRecord.getBatsmanId();
        this.name = name;
        this.runs += ballRecord.getScore();
        this.balls += 1;
        if(ballRecord.getScore() == 4) this.fours += 1;
        if(ballRecord.getScore() == 6) this.sixes += 1;
        this.strikeRate = new BigDecimal(String.valueOf((Float.valueOf(this.runs)/this.balls)*100))
                .setScale(2, RoundingMode.HALF_UP)
                .toPlainString()
                .replaceAll("\\.00","");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public String getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public UUID getBatsmanId() {
        return batsmanId;
    }

    public void setBatsmanId(UUID batsmanId) {
        this.batsmanId = batsmanId;
    }
}
