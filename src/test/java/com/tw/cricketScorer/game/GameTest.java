package com.tw.cricketScorer.game;

import com.tw.cricketScorer.player.Player;
import com.tw.cricketScorer.team.Team;
import cricketScorer.db.gen.tables.records.GameRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {


    @Mock
    private GameRecord gameRecord;

    @Test
    public void shouldReturnTeam1Data() {

        when(gameRecord.getTeam1()).thenReturn("Team 1");
        when(gameRecord.getTeam2()).thenReturn("Team 2");
        when(gameRecord.getTeam1Overs()).thenReturn(new BigDecimal(10.4f));
        when(gameRecord.getTeam2Overs()).thenReturn(new BigDecimal(20.0f));
        when(gameRecord.getTeam1Score()).thenReturn(0);
        when(gameRecord.getTeam2Score()).thenReturn(0);
        when(gameRecord.getTeam1Wickets()).thenReturn(0);
        when(gameRecord.getTeam2Wickets()).thenReturn(0);
        when(gameRecord.getBatsman1()).thenReturn(null);
        when(gameRecord.getBatsman2()).thenReturn(null);
        var testTeam = new Team("Team 1",
                Arrays.asList(new Player(UUID.randomUUID(), "Sarang"),
                        new Player(UUID.randomUUID(), "bala"),
                        new Player(UUID.randomUUID(), "Sethu")),
                true, 0, 0, 10.4f);

        Game game = new Game(gameRecord, testTeam.getPlayers(), new ArrayList<>());

        Team team1 = game.getTeams().get(0);
        assertEquals("Team 1", team1.toString());
        assertEquals(testTeam.getPlayers(), team1.getPlayers());
        assertEquals(testTeam.getScore(), team1.getScore());
        assertEquals(testTeam.getWickets(), team1.getWickets());
        assertEquals(testTeam.getOvers(), team1.getOvers(), 0);
    }

    @Test
    public void shouldReturnTeam2Data() {

        when(gameRecord.getTeam1()).thenReturn("Team 1");
        when(gameRecord.getTeam2()).thenReturn("Team 2");
        when(gameRecord.getTeam1Overs()).thenReturn(new BigDecimal(10.4f));
        when(gameRecord.getTeam2Overs()).thenReturn(new BigDecimal(20.0f));
        when(gameRecord.getTeam1Score()).thenReturn(0);
        when(gameRecord.getTeam2Score()).thenReturn(0);
        when(gameRecord.getTeam1Wickets()).thenReturn(0);
        when(gameRecord.getTeam2Wickets()).thenReturn(0);
        when(gameRecord.getBatsman1()).thenReturn(null);
        when(gameRecord.getBatsman2()).thenReturn(null);
        var testTeam = new Team("Team 2", Arrays.asList(new Player(UUID.randomUUID(), "Sarang"),
                new Player(UUID.randomUUID(), "bala"), new Player(UUID.randomUUID(), "Sethu")), true, 0, 0, 20.0f);
        Game game = new Game(gameRecord, testTeam.getPlayers(), testTeam.getPlayers());
        assertEquals(testTeam.toString(), game.getTeams().get(1).toString());
        assertEquals(testTeam.getPlayers(), game.getTeams().get(1).getPlayers());
        assertEquals(testTeam.getScore(), game.getTeams().get(1).getScore());

        assertEquals(testTeam.getWickets(), game.getTeams().get(1).getWickets());

        assertEquals(testTeam.getOvers(), game.getTeams().get(1).getOvers(), 0);
    }

    @Test
    public void shouldReturnCurentBatsmenAsFirstTwoPlayersOfATeamWhenGameColumnsAreEmpty() {
        when(gameRecord.getTeam1()).thenReturn("Team 1");
        when(gameRecord.getTeam2()).thenReturn("Team 2");
        when(gameRecord.getTeam1Overs()).thenReturn(new BigDecimal(10.4f));
        when(gameRecord.getTeam2Overs()).thenReturn(new BigDecimal(20.0f));
        when(gameRecord.getTeam1Score()).thenReturn(0);
        when(gameRecord.getTeam2Score()).thenReturn(0);
        when(gameRecord.getTeam1Wickets()).thenReturn(0);
        when(gameRecord.getTeam2Wickets()).thenReturn(0);
        when(gameRecord.getBatsman1()).thenReturn(null);
        when(gameRecord.getBatsman2()).thenReturn(null);
        var player1Id = UUID.randomUUID();
        var player2Id = UUID.randomUUID();
        var testTeam = new Team("Team 2", Arrays.asList(new Player(player1Id, "Sarang"),
                new Player(player2Id, "bala"), new Player(UUID.randomUUID(), "Sethu")), true, 0, 0, 20.0f);

        Game game = new Game(gameRecord, testTeam.getPlayers(), testTeam.getPlayers());

        assertEquals(player1Id, game.getCurrentBatsmen().get(0).getId());
        assertEquals(player2Id, game.getCurrentBatsmen().get(1).getId());


    }

    @Test
    public void shouldReturnTheCurrentBatsmenAsIsInTheGameTableWhenTheBatsmenColumnAreNotEmpty() {
        var batsMan1Id = UUID.randomUUID();
        var batsMan2Id = UUID.randomUUID();
        GameRecord record = new GameRecord(UUID.randomUUID(),
                "Team 1",
                "Team 2",
                new Timestamp(System.currentTimeMillis()),
                "2020",
                0,
                0,
                0,
                0,
                new BigDecimal(10.4f),
                new BigDecimal(0.0f),
                batsMan1Id,
                batsMan2Id);

        var team1 = new Team("Team 1",
                Arrays.asList(new Player(batsMan1Id, "Sarang"),
                              new Player(batsMan2Id, "bala"),
                              new Player(UUID.randomUUID(), "Sethu")), true, 0, 0, 10.4f);

        Game game = new Game(record, team1.getPlayers(), team1.getPlayers());
        assertEquals(batsMan1Id, game.getCurrentBatsmen().get(0).getId());
        assertEquals(batsMan2Id, game.getCurrentBatsmen().get(1).getId());

    }

}