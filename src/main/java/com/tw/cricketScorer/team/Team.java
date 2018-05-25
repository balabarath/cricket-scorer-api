package com.tw.cricketScorer.team;

import com.tw.cricketScorer.player.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Team {

    private boolean isPlaying;
    private int  score;
    private int wickets;
    private float overs;
    private String name;
    private List<Player> players;



    public Team(String teamName, List<Player> players, boolean isPlaying,int score,int wickets,float overs){
        this.name = teamName;
        this.players = players;
        this.isPlaying = isPlaying;
        this.score = score;
        this.wickets = wickets;
        this.overs = overs;
    }

    public String toString()
    {
        return  name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }


    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public float getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public Optional<Player> find(UUID playerId)
    {
        return players.stream().filter(player -> player.getId().equals(playerId)).findFirst();
    }

    public boolean getIsPlaying(){
        return isPlaying;
    }
}
