package com.tw.cricketScorer.game;

import com.tw.cricketScorer.player.Player;
import com.tw.cricketScorer.team.Team;
import cricketScorer.db.gen.tables.records.GameRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {


    @Mock
    private GameRecord gameRecord;

    @Test
    public void shouldReturnTeam1Data(){

        when(gameRecord.getTeam1()).thenReturn("Team 1");
        when(gameRecord.getTeam2()).thenReturn("Team 2");
        var testTeam = new Team("Team 1",new ArrayList<>(), true,0,0,0);
        Game game = new Game(gameRecord,testTeam.getPlayers(),new ArrayList<Player>());
        assertEquals("Team 1",game.getTeams().get(0).toString());
        assertEquals(testTeam.getPlayers(),game.getTeams().get(0).getPlayers());

        assertEquals(testTeam.getScore(),game.getTeams().get(0).getScore());

        assertEquals(testTeam.getWickets(),game.getTeams().get(0).getWickets());

        assertEquals(testTeam.getOvers(),game.getTeams().get(0).getOvers());
    }

    @Test
    public void shouldReturnTeam2Data(){

        when(gameRecord.getTeam1()).thenReturn("Team 1");
        when(gameRecord.getTeam2()).thenReturn("Team 2");
        var testTeam = new Team("Team 2",new ArrayList<>(), true,0,0,0);
        Game game = new Game(gameRecord,testTeam.getPlayers(),testTeam.getPlayers());
        assertEquals(testTeam.toString(),game.getTeams().get(1).toString());
        assertEquals(testTeam.getPlayers(),game.getTeams().get(1).getPlayers());
        assertEquals(testTeam.getScore(),game.getTeams().get(1).getScore());

        assertEquals(testTeam.getWickets(),game.getTeams().get(1).getWickets());

        assertEquals(testTeam.getOvers(),game.getTeams().get(1).getOvers());
    }

}