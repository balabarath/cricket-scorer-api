package com.tw.cricketScorer.player;

import java.util.UUID;

public class Player {

    private UUID playerID;
    private String Name;


    public Player(UUID id, String name)
    {
        this.Name = name;
        this.playerID = id;
    }
    public UUID getPlayerID() {
        return playerID;
    }

    public void setPlayerID(UUID playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
