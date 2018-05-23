package com.tw.cricketScorer.player;

import cricketScorer.db.gen.tables.records.PlayersRecord;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static cricketScorer.db.gen.tables.Players.PLAYERS;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private DSLContext dsl;

    @Test
    public void shouldReturn11PlayersForATeam()
    {
        var players = playerRepository.getPlayers("Team 1");
        assertEquals(11,players.size());

    }

    @Test
    public void copyForTestShouldReturn11PlayersForATeam()
    {
        var players = playerRepository.getPlayers("Team 1");
        assertEquals(11,players.size());

    }

    @Test
    public void shouldReturnPlayerRecordForGivenPlayerId() {
        var player = dsl.selectFrom(PLAYERS).fetchAny();
        var retPlayer = playerRepository.getPlayer(player.getId());
        assertEquals(player.getName(), retPlayer.getName());
        assertEquals(player.getTeamName(), retPlayer.getTeamName());
    }
}