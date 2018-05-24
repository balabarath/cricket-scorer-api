package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.records.GameRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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


    public GameRecord getGameInformationForId(UUID gameId) {
        return dsl.selectFrom(GAME).where(GAME.ID.eq(gameId)).fetchOne();
    }

    public void update(GameRecord game) {

        dsl.update(GAME)
                .set(GAME.TEAM1_SCORE,game.getTeam1Score())
                .set(GAME.TEAM2_SCORE,game.getTeam2Score())
                .where(GAME.ID.eq(game.getId())).execute();
    }

    public String getCurrentBattingTeam(UUID gameId) {

        GameRecord gameRecord = dsl.selectFrom(GAME).where(GAME.ID.eq(gameId)).fetchOne();
        return gameRecord.getTeam1();
    }


}
