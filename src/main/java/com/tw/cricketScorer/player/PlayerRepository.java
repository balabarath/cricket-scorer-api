package com.tw.cricketScorer.player;

import cricketScorer.db.gen.tables.Players;
import cricketScorer.db.gen.tables.records.PlayersRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static cricketScorer.db.gen.tables.Players.PLAYERS;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@Transactional
public class PlayerRepository {



    private DSLContext dsl;

    @Autowired
    public PlayerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<Player> getPlayers(String teamName) {
        return (dsl.selectFrom(PLAYERS).where(PLAYERS.TEAM_NAME.eq(teamName))
                .fetchInto(PlayersRecord.class)).stream()
                .map(s-> new Player(s.getId(),s.getName()))
                .collect(Collectors.toList());
    }
}
