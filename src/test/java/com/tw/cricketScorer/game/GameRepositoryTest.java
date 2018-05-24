package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.records.GameRecord;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static cricketScorer.db.gen.tables.Game.GAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private DSLContext dsl;

    @Test
    public void shouldReturnGameInformation() {
        GameRecord game = gameRepository.getGameInformation();
        assertThat(game.getTeam1()).isEqualTo("Team 1");
        assertThat(game.getTeam2()).isEqualTo("Team 2");
        assertThat(game.getGameType()).isEqualTo(GameType.TwentyTwenty.dbType());
    }

    @Test
    public void shouldReturnGameInformationForGivenId() {
        GameRecord game = dsl.selectFrom(GAME).fetchOne();
        GameRecord actualRecord = gameRepository.getGameInformationForId(game.getId());

        assertThat(game.getTeam1()).isEqualTo(actualRecord.getTeam1());
        assertThat(game.getTeam2()).isEqualTo(actualRecord.getTeam2());
    }

    @Test
    public void shouldUpdateGameRecordInfoForGivenId() {
        GameRecord game = dsl.selectFrom(GAME).fetchOne();
        game.setTeam1Score(50);
        game.setTeam2Score(30);
        gameRepository.update(game);

        GameRecord actualRecord = gameRepository.getGameInformationForId(game.getId());

        assertEquals(game.getTeam1Score(), actualRecord.getTeam1Score());
        assertEquals(game.getTeam2Score(), actualRecord.getTeam2Score());
        assertEquals(game.getTeam1Score(),actualRecord.getTeam1Score());
        assertEquals(game.getTeam2Score(),actualRecord.getTeam2Score());
    }


    @Test
    public void shouldReturnTeam1AsCurrentBattingTeamWhenGameIsNotStarted() {

        GameRecord game = dsl.selectFrom(GAME).fetchOne();
        String team1 = game.getTeam1();
        assertEquals(team1,gameRepository.getCurrentBattingTeam(game.getId()));

    }



}