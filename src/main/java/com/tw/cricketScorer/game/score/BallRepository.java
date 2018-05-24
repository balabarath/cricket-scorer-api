package com.tw.cricketScorer.game.score;

import cricketScorer.db.gen.tables.records.BallRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static cricketScorer.db.gen.tables.Ball.BALL;

@Repository
@Transactional
public class BallRepository {


    private DSLContext dsl;

    @Autowired
    public BallRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void save(BallRecord ball) {
        ball.setId(UUID.randomUUID());
        dsl.insertInto(BALL).set(ball).execute();

    }

    public List<BallRecord> getCurrentlyPlayingPlayers(String team) {
       return dsl.selectFrom(BALL).where(BALL.TEAM_NAME.eq(team)).fetchInto(BallRecord.class);
    }
}
