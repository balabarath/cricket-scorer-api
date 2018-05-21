package com.tw.cricketScorer.game;

import com.tw.cricketScorer.player.Player;
import com.tw.cricketScorer.team.Team;
import cricketScorer.db.gen.tables.records.GameRecord;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Team> teams;
    private String team1;
    private String team2;

    public Game(GameRecord gameRecord, List<Player> team1Players, List<Player> team2Players) {
        teams = new ArrayList<>();
        teams.add(new Team(gameRecord.getTeam1(),team1Players,true));
        teams.add(new Team(gameRecord.getTeam2(),team2Players,false));

    }


    public List<Team> getTeams() {

        return teams;
    }
}
