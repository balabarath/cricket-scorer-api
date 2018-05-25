package com.tw.cricketScorer.game;

import com.tw.cricketScorer.game.score.Score;
import com.tw.cricketScorer.game.score.ScoreService;
import com.tw.cricketScorer.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GameController {

    private GameRepository gameRepository;
    private PlayerRepository playerRepository;
    private ScoreService scoreService;
    private GameService gameService;

    @Autowired
    public GameController(GameRepository gameRepository, PlayerRepository playerRepository,ScoreService scoreService, GameService gameService) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.scoreService = scoreService;
        this.gameService = gameService;
    }

    @GetMapping("/game")
    public Game game() {

        return gameService.getGameRecord();

    }

    @PostMapping("/game/{id}")
    public ResponseEntity addScore(@PathVariable(value = "id") String id, @RequestBody Score score){

        score.setGameId(UUID.fromString(id));
        try {
            scoreService.addScore(score);
        }
        catch (Exception e) {
            return new ResponseEntity<>("failure", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);

    }

    @GetMapping("/game/{id}/scoredetails")
    public List<BatsmanDetails> getBatsmenDetails(@PathVariable(value = "id") UUID gameId){

        return gameService.getBattingTeamScoreDetails(gameId);
    }



}
