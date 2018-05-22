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

        teams.add(new Team(gameRecord.getTeam1(),team1Players,
                true,
                 gameRecord.getTeam1Score(),
                 gameRecord.getTeam1Wickets()
                ,gameRecord.getTeam1Overs()));
        teams.add(new Team(gameRecord.getTeam2(),team2Players,false,gameRecord.getTeam2Score(),gameRecord.getTeam2Wickets(),gameRecord.getTeam2Overs()));

    }


    public List<Team> getTeams() {

        return teams;
    }
}
