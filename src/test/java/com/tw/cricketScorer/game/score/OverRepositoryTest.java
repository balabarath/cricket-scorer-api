package com.tw.cricketScorer.game.score;

import com.tw.cricketScorer.game.GameRepository;
import cricketScorer.db.gen.tables.records.GameRecord;
import cricketScorer.db.gen.tables.records.OverRecord;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static cricketScorer.db.gen.tables.Over.OVER;
import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@Transactional
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
        overRecord.setId(UUID.randomUUID());
        overRecord.setNumber(1);
        overRecord.setTeamName(gameRecord.getTeam1());
        overRecord.setGame(gameRecord.getId());
        overRepository.save(overRecord);

        OverRecord retRecord = dslContext.selectFrom(OVER).fetchOne();

        assertEquals(overRecord.getTeamName(),retRecord.getTeamName());
        assertEquals(overRecord.getNumber(),retRecord.getNumber());

    }

    @Test
    public void shouldReturnOverRecordForGivenOverNumber(){
        GameRecord gameRecord = gameRepository.getGameInformation();

        OverRecord overRecord = new OverRecord();
        overRecord.setId(UUID.randomUUID());
        overRecord.setNumber(1);
        overRecord.setTeamName(gameRecord.getTeam1());
        overRecord.setGame(gameRecord.getId());
        overRepository.save(overRecord);

        Optional<OverRecord> retOverRecord = overRepository.getOverDetails(overRecord.getNumber(),gameRecord.getTeam1());
        assertThat(retOverRecord.isPresent()).isEqualTo(true);
        assertThat(retOverRecord.get().getTeamName()).isEqualTo(overRecord.getTeamName());
        assertThat(retOverRecord.get().getNumber()).isEqualTo(overRecord.getNumber());
    }

    @Test
    public void shouldReturnEmptyOptionalWHenNoOverRecordFound() {
        Optional<OverRecord> overRecord = overRepository.getOverDetails(123,"non existent team");
        assertThat(overRecord.isPresent()).isEqualTo(false);
    }


}