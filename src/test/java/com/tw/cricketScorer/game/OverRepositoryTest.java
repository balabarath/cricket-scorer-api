package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.records.GameRecord;
import cricketScorer.db.gen.tables.records.OverRecord;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static cricketScorer.db.gen.tables.Over.OVER;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class OverRepositoryTest {

    @Autowired
    private OverRepository overRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private DSLContext dslContext;

    @Test
    public void shouldSaveOverInformation() {
        GameRecord gameRecord = gameRepository.getGameInformation();

        OverRecord overRecord = new OverRecord();
        overRecord.setNumber(1);
        overRecord.setTeamName(gameRecord.getTeam1());
        overRecord.setGame(gameRecord.getId());
        overRepository.save(overRecord);

        OverRecord retRecord = dslContext.selectFrom(OVER).fetchOne();

        assertEquals(overRecord.getTeamName(),retRecord.getTeamName());
        assertEquals(overRecord.getNumber(),retRecord.getNumber());

    }



}