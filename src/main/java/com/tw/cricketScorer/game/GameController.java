package com.tw.cricketScorer.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {


    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/game")
    public Game game() {
        return new Game(gameRepository.getGameInformation());
    }
}
