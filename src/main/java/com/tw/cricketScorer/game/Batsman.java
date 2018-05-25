package com.tw.cricketScorer.game;

import java.util.UUID;

public class Batsman {

    private UUID id;
    private String name;
    private Boolean isOnStrike;

    public Batsman(UUID id, String name, Boolean isOnStrike) {
        this.id = id;
        this.name = name;
        this.isOnStrike = isOnStrike;
    }

    public Boolean getOnStrike() {
        return isOnStrike;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
