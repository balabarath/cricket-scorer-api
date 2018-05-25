package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.records.BallRecord;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class BatsmanDetailsTest {

    @Test
    public void shouldUpdateBatsmanDetailsFromBallRecordForBoundryScored(){
        BatsmanDetails batsmanDetails = new BatsmanDetails();
        BallRecord ballRecord = new BallRecord();

        ballRecord.setBatsmanId(UUID.randomUUID());
        ballRecord.setScore(4);
        String batsmanName = "Test Name";

        batsmanDetails.buildFromBallRecord(ballRecord,batsmanName);

        Assertions.assertThat(ballRecord.getScore()).isEqualTo(batsmanDetails.getRuns());
        Assertions.assertThat(1).isEqualTo(batsmanDetails.getBalls());
        Assertions.assertThat(1).isEqualTo(batsmanDetails.getFours());
        Assertions.assertThat(0).isEqualTo(batsmanDetails.getSixes());
        Assertions.assertThat(ballRecord.getBatsmanId()).isEqualTo(batsmanDetails.getBatsmanId());

    }

    @Test
    public void shouldUpdateBatsmanDetailsFromBallRecordForSixScored(){
        BatsmanDetails batsmanDetails = new BatsmanDetails();
        BallRecord ballRecord = new BallRecord();

        ballRecord.setBatsmanId(UUID.randomUUID());
        ballRecord.setScore(6);
        String batsmanName = "Test Name";

        batsmanDetails.buildFromBallRecord(ballRecord,batsmanName);

        Assertions.assertThat(ballRecord.getScore()).isEqualTo(batsmanDetails.getRuns());
        Assertions.assertThat(1).isEqualTo(batsmanDetails.getBalls());
        Assertions.assertThat(1).isEqualTo(batsmanDetails.getSixes());
        Assertions.assertThat(0).isEqualTo(batsmanDetails.getFours());
        Assertions.assertThat(ballRecord.getBatsmanId()).isEqualTo(batsmanDetails.getBatsmanId());

    }

    @Test
    public void shouldUpdateBatsmanDetailsFromBallRecordForNormalRunsScored(){
        BatsmanDetails batsmanDetails = new BatsmanDetails();
        BallRecord ballRecord = new BallRecord();

        ballRecord.setBatsmanId(UUID.randomUUID());
        ballRecord.setScore(3);
        String batsmanName = "Test Name";

        batsmanDetails.buildFromBallRecord(ballRecord,batsmanName);

        Assertions.assertThat(ballRecord.getScore()).isEqualTo(batsmanDetails.getRuns());
        Assertions.assertThat(1).isEqualTo(batsmanDetails.getBalls());
        Assertions.assertThat(0).isEqualTo(batsmanDetails.getSixes());
        Assertions.assertThat(0).isEqualTo(batsmanDetails.getFours());
        Assertions.assertThat(ballRecord.getBatsmanId()).isEqualTo(batsmanDetails.getBatsmanId());

    }


    @Test
    public void shouldRoundStrikeRate(){
        BatsmanDetails batsmanDetails = new BatsmanDetails();
        batsmanDetails.setBalls(2);
        batsmanDetails.setRuns(10);
        BallRecord ballRecord = new BallRecord();

        ballRecord.setBatsmanId(UUID.randomUUID());
        ballRecord.setScore(1);
        String batsmanName = "Test Name";

        batsmanDetails.buildFromBallRecord(ballRecord,batsmanName);

        Assertions.assertThat(batsmanDetails.getStrikeRate()).isEqualTo("366.67");
    }




}