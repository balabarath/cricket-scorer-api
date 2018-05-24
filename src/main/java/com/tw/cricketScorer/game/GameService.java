package com.tw.cricketScorer.game;

import com.tw.cricketScorer.game.score.BallRepository;
import com.tw.cricketScorer.player.Player;
import com.tw.cricketScorer.player.PlayerRepository;
import cricketScorer.db.gen.tables.records.BallRecord;
import cricketScorer.db.gen.tables.records.PlayersRecord;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class GameService {


    private GameRepository gameRepository;
    private PlayerRepository playerRepository;
    private BallRepository ballRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, BallRepository ballRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.ballRepository = ballRepository;
    }

    public Game getGameRecord() {

        var team1 = gameRepository.getGameInformation().getTeam1();
        var team2 = gameRepository.getGameInformation().getTeam2();
        return new Game(gameRepository.getGameInformation(),
                playerRepository.getPlayers(team1),
                playerRepository.getPlayers(team2));
    }


    public List<BatsmanDetails> getBattingTeamPlayerScoreDetails(UUID gameId) {
        String currentBattingTeam = gameRepository.getCurrentBattingTeam(gameId);
        List<Player> playersForBattingTeam = playerRepository.getPlayers(currentBattingTeam);
        List<BallRecord> currentlyPlayingPlayers = ballRepository.getCurrentlyPlayingPlayers(currentBattingTeam);
        Map<UUID, BatsmanDetails> batsmanDetailsMap = new HashMap<>();

        for(BallRecord ballRecord : currentlyPlayingPlayers) {
            BatsmanDetails batsmanDetails = batsmanDetailsMap.getOrDefault(ballRecord.getBatsmanId(), new BatsmanDetails());
            Player player = playersForBattingTeam.stream()
                                                 .filter(p->p.getId().equals(ballRecord.getBatsmanId()))
                                                 .findFirst().get();
            batsmanDetails.buildFromBallRecord(ballRecord,player.getName());
            batsmanDetailsMap.putIfAbsent(ballRecord.getBatsmanId(),batsmanDetails);
        }

        return new ArrayList<>(batsmanDetailsMap.values());
    }



}
