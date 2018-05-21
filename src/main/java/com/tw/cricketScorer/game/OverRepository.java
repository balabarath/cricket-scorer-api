package com.tw.cricketScorer.game;


import cricketScorer.db.gen.tables.records.OverRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            overRecord.setId(UUID.randomUUID());
            dsl.insertInto(OVER).set(overRecord).execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public OverRecord getOverDetails(Integer overRecordNumber, String teamName) {
        return dsl.selectFrom(OVER).where(OVER.NUMBER.eq(overRecordNumber))
                .and(OVER.TEAM_NAME.eq(teamName)).fetchOne();
    }
}
