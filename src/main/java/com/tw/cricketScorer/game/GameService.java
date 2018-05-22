package com.tw.cricketScorer.game;

import com.tw.cricketScorer.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {


    private GameRepository gameRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public Game getGameRecord() {

        var team1= gameRepository.getGameInformation().getTeam1();
        var team2 = gameRepository.getGameInformation().getTeam2();
        return new Game(gameRepository.getGameInformation(),
                playerRepository.getPlayers(team1),
                playerRepository.getPlayers(team2));
    }
}
