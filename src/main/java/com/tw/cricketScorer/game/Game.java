package com.tw.cricketScorer.game;

import com.tw.cricketScorer.player.Player;
import com.tw.cricketScorer.team.Team;
import cricketScorer.db.gen.tables.records.GameRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Game {
    private List<Team> teams;
    private UUID gameId;
    private List<Batsman> currentBatsmen = new ArrayList<>();

    public Game(GameRecord gameRecord, List<Player> team1Players, List<Player> team2Players) {
        teams = Arrays.asList(createTeam1(gameRecord, team1Players),
                              createTeam2(gameRecord, team2Players));
        gameId = gameRecord.getId();

        setCurrentBatsmen(gameRecord);

    }

    private void setCurrentBatsmen(GameRecord gameRecord)
    {
        if (gameRecord.getBatsman1() == null && gameRecord.getBatsman2() == null) {
            Player player1 = teams.get(0).getPlayers().get(0);
            Player player2 = teams.get(0).getPlayers().get(1);
            this.currentBatsmen.add(new Batsman(player1.getId(), player1.getName(), false));
            this.currentBatsmen.add(new Batsman(player2.getId(), player2.getName(), false));
        }
        else
        {
            var player1 = getPlayingTeam().find(gameRecord.getBatsman1());
            var player2 = getPlayingTeam().find(gameRecord.getBatsman2());
            this.currentBatsmen.add(new Batsman(player1.get().getId(),player1.get().getName(),false));
            this.currentBatsmen.add(new Batsman(player2.get().getId(),player2.get().getName(), false));


        }

    }
    private Team getPlayingTeam()
    {
        var playingTeam = teams.stream().filter(team-> team.getIsPlaying()==true).findFirst();
        if(playingTeam.isPresent())
        {
            return playingTeam.get();
        }
        else {
            return teams.get(0);
        }
    }


    private Team createTeam1(GameRecord gameRecord, List<Player> team1Players) {
        return new Team(gameRecord.getTeam1(),
                        team1Players,
                        true,
                        gameRecord.getTeam1Score(),
                        gameRecord.getTeam1Wickets(),
                        gameRecord.getTeam1Overs().floatValue());
    }

    private Team createTeam2(GameRecord gameRecord, List<Player> team2Players) {
        return new Team(gameRecord.getTeam2(),
                team2Players,
                false,
                gameRecord.getTeam2Score(),
                gameRecord.getTeam2Wickets(),
                gameRecord.getTeam2Overs().floatValue());
    }


    public List<Team> getTeams() {
        return teams;
    }


    public UUID getGameId() {
        return gameId;
    }

    public List<Batsman> getCurrentBatsmen() {
        return currentBatsmen;
    }
}
