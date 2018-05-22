package com.tw.cricketScorer.game;

import com.tw.cricketScorer.player.Player;
import com.tw.cricketScorer.player.PlayerRepository;
import com.tw.cricketScorer.team.Team;
import cricketScorer.db.gen.tables.records.GameRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {


    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRecord gameRecord;

    @Test
    public void shouldReturnGameData(){
        var newplayyer1UUID = new UUID(1234,0);
        var newplayyer2UUID = new UUID(5678,0);
        when(gameRecord.getTeam1()).thenReturn("Team 1");
        when(gameRecord.getTeam2()).thenReturn("Team 2");
        when(gameRecord.getTeam1Overs()).thenReturn(new BigDecimal(10.4f));
        when(gameRecord.getTeam2Overs()).thenReturn(new BigDecimal(20.0f));
        when(gameRecord.getTeam1Score()).thenReturn(0);
        when(gameRecord.getTeam2Score()).thenReturn(0);
        when(gameRecord.getTeam1Wickets()).thenReturn(0);
        when(gameRecord.getTeam2Wickets()).thenReturn(0);
        when(gameRepository.getGameInformation()).thenReturn(gameRecord);
        when(playerRepository.getPlayers("Team 1")).thenReturn(Arrays.asList(new Player(newplayyer1UUID,"Sarang")));
        when(playerRepository.getPlayers("Team 2")).thenReturn(Arrays.asList(new Player(newplayyer2UUID,"Sethu")));

        var service = new GameService(gameRepository,playerRepository);
        var game = service.getGameRecord();
        assertEquals("Team 1",game.getTeams().get(0).getName());
        assertEquals("Team 2",game.getTeams().get(1).getName());


    }

}