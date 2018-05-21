package com.tw.cricketScorer.game;

import cricketScorer.db.gen.tables.records.GameRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void shouldReturnGameInformation() {
        GameRecord game = gameRepository.getGameInformation();
        assertThat(game.getTeam1()).isEqualTo("Team 1");
        assertThat(game.getTeam2()).isEqualTo("Team 2");
        assertThat(game.getGameType()).isEqualTo(GameType.TwentyTwenty.dbType());
    }
}