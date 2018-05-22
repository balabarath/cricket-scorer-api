package com.tw.cricketScorer.game.score;


import cricketScorer.db.gen.tables.records.OverRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static cricketScorer.db.gen.tables.Over.OVER;
import static org.jooq.impl.DSL.*;


@Repository
@Transactional
public class OverRepository {

    private DSLContext dsl;

    @Autowired
    public OverRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void save(OverRecord overRecord) {
        try {
            dsl.insertInto(OVER).set(overRecord).execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Optional<OverRecord> getOverDetails(Integer overNumber, String teamName) {
        return Optional.ofNullable(dsl.selectFrom(OVER).where(OVER.NUMBER.eq(overNumber))
                .and(OVER.TEAM_NAME.eq(teamName)).fetchOne());
    }
}
