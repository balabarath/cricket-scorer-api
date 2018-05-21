package com.tw.cricketScorer.team;

import com.tw.cricketScorer.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Team {

    private String teamName;
    private List<Player> players;
    private boolean isPlaying;

    public Team(String teamName, List<Player> players, boolean isPlaying){
        this.teamName = teamName;
        this.players = players;
        this.isPlaying = isPlaying;
    }

    public String toString()
    {
        return  teamName;
    }

    public List<Player> getPlayers() {
        return players.stream().map(s-> new Player(s.getPlayerID(),s.getName()))
                .collect(Collectors.toList());
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
