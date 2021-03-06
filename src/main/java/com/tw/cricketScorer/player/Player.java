package com.tw.cricketScorer.player;

import java.util.UUID;

public class Player {

    private UUID id;
    private String name;


    public Player(UUID id, String name)
    {
        this.name = name;
        this.id = id;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
