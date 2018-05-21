package com.tw.cricketScorer.game;

import com.tw.cricketScorer.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/game")
public class GameController {

    private GameRepository gameRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public GameController(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping("")
    public Game game() {

       var team1= gameRepository.getGameInformation().getTeam1();
       var team2 = gameRepository.getGameInformation().getTeam2();
       return new Game(gameRepository.getGameInformation(),
               playerRepository.getPlayers(team1),
               playerRepository.getPlayers(team2));
    }




}
