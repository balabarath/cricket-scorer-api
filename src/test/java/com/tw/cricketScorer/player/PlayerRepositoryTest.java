package com.tw.cricketScorer.player;

import cricketScorer.db.gen.tables.records.PlayersRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;
    @Test
    public void shouldReturn11PlayersForATeam()
    {
        var players = playerRepository.getPlayers("Team 1");
        assertEquals(11,players.size());

    }

}