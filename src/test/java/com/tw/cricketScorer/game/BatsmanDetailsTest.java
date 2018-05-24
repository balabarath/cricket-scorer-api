package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.records.BallRecord;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class BatsmanDetailsTest {

    @Test
    public void shouldUpdateBatsmanDetailsFromBallRecord(){
        BatsmanDetails batsmanDetails = new BatsmanDetails();
        BallRecord ballRecord = new BallRecord();
        ballRecord.setScore(4);
        String batsmanName = "Test Name";

        batsmanDetails.buildFromBallRecord(ballRecord,batsmanName);

        assertTrue(ballRecord.getScore()==batsmanDetails.getRuns());
    }

}