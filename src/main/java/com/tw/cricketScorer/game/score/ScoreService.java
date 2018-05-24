package com.tw.cricketScorer.game.score;

import com.tw.cricketScorer.game.GameRepository;
import com.tw.cricketScorer.player.PlayerRepository;
import cricketScorer.db.gen.tables.records.BallRecord;
import cricketScorer.db.gen.tables.records.GameRecord;
import cricketScorer.db.gen.tables.records.OverRecord;
import cricketScorer.db.gen.tables.records.PlayersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreService {

    private BallRepository ballRepository;
    private OverRepository overRepository;
    private GameRepository gameRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public ScoreService(BallRepository ballRepository, OverRepository overRepository, GameRepository gameRepository, PlayerRepository playerRepository) {
        this.ballRepository = ballRepository;
        this.overRepository = overRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void addScore(Score score) {
        PlayersRecord batsman = playerRepository.getPlayer(score.getPlayerId());

        OverRecord over = createOverRecordIfNotExisting(score, batsman);

        createBallRecord(score, batsman, over);

        updateScoreInGameRecord(score, batsman);
    }

    private void updateScoreInGameRecord(Score score, PlayersRecord batsman) {
        GameRecord game = gameRepository.getGameInformationForId(score.getGameId());
        int newScore;
        if(batsman.getTeamName().equals(game.getTeam1())) {
            newScore = game.getTeam1Score() + score.getScore();
            game.setTeam1Score(newScore);
        } else {
            newScore = game.getTeam2Score() + score.getScore();
            game.setTeam2Score(newScore);
        }
        gameRepository.update(game);
    }

    private void createBallRecord(Score score, PlayersRecord batsman, OverRecord over) {
        BallRecord ball = new BallRecord();

        ball.setTeamName(batsman.getTeamName());
        ball.setBatsmanId(batsman.getId());
        ball.setOverId(over.getId());
        ball.setScore(score.getScore());

        ballRepository.save(ball);
    }

    private OverRecord createOverRecordIfNotExisting(Score score, PlayersRecord batsman) {
        Optional<OverRecord> over = overRepository.getOverDetails(score.getOverNumber(),
                batsman.getTeamName());

        return over.orElseGet(()->createNewOverRecord(score, batsman));
    }

    private OverRecord createNewOverRecord(Score score, PlayersRecord batsman) {
        OverRecord overRecord = new OverRecord(UUID.randomUUID(),
                score.getOverNumber(),batsman.getTeamName(),score.getGameId());
        overRepository.save(overRecord);
        return overRecord;
    }
}
