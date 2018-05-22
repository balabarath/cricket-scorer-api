package com.tw.cricketScorer.game;

import com.tw.cricketScorer.game.score.Score;
import com.tw.cricketScorer.game.score.ScoreService;
import com.tw.cricketScorer.player.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private ScoreService scoreService;


    @Test
    public void shouldSaveScoreData(){

        GameController gameController = new GameController(gameRepository,playerRepository,scoreService);
        UUID gameId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        Score score = new Score(playerId, 5, 4, gameId);
        gameController.addScore(gameId.toString(),score);

        verify(scoreService).addScore(score);
    }




}

