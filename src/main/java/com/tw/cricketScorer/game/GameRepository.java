package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.Game;
import cricketScorer.db.gen.tables.records.GameRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static cricketScorer.db.gen.tables.Game.GAME;

@Repository
@Transactional
public class GameRepository {

    private DSLContext dsl;

    @Autowired
    public GameRepository(DSLContext dsl) {
        this.dsl = dsl;
    }


    public GameRecord getGameInformation() {
        return dsl.selectFrom(GAME).fetchOne();
    }
}
