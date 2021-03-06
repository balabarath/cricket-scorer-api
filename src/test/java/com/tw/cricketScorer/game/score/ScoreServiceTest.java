package com.tw.cricketScorer.game.score;

import com.tw.cricketScorer.game.Batsman;
import com.tw.cricketScorer.game.GameRepository;
import com.tw.cricketScorer.player.PlayerRepository;
import cricketScorer.db.gen.tables.records.BallRecord;
import cricketScorer.db.gen.tables.records.GameRecord;
import cricketScorer.db.gen.tables.records.OverRecord;
import cricketScorer.db.gen.tables.records.PlayersRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScoreServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private BallRepository ballRepository;

    @Mock
    private OverRepository overRepository;

    @Captor
    private ArgumentCaptor<GameRecord> gameRecordArgumentCaptor;

    @Captor
    private ArgumentCaptor<BallRecord> ballRecordArgumentCaptor;

    @Captor
    private ArgumentCaptor<OverRecord> overRecordArgumentCaptor;


    @Test
    public void shouldAddScoreToBallOverAndGameTable(){

        ScoreService scoreService = new ScoreService(ballRepository,overRepository,
                gameRepository,playerRepository);

        UUID gameId = UUID.randomUUID();
        UUID overId = UUID.randomUUID();
        UUID batsmanOnstrikeId = UUID.randomUUID();
        UUID nonSrikerId = UUID.randomUUID();



        GameRecord game = getGameRecord(gameId);

        PlayersRecord batsmanOnStrike = new PlayersRecord(batsmanOnstrikeId, "Test Batsman", "Team 1");
        PlayersRecord nonStriker = new PlayersRecord(nonSrikerId, "Test Batsman2", "Team 1");

        Score score = new Score(Arrays.asList(new Batsman(batsmanOnstrikeId,batsmanOnStrike.getName(),true),
                                              new Batsman(nonSrikerId,nonStriker.getName(),false)),
                                            5, 4, gameId);

        OverRecord over = new OverRecord(overId, score.getOver(), batsmanOnStrike.getTeamName(), gameId);


        Optional<OverRecord> overRecord = Optional.of(over);

        when(playerRepository.getPlayer(batsmanOnstrikeId)).thenReturn(batsmanOnStrike);
        when(overRepository.getOverDetails(score.getOver(), batsmanOnStrike.getTeamName())).thenReturn(overRecord);
        when(gameRepository.getGameInformationForId(gameId)).thenReturn(game);

        scoreService.addScore(score);
        verify(ballRepository).save(ballRecordArgumentCaptor.capture());
        BallRecord capturedBallRecord = ballRecordArgumentCaptor.getValue();
        assertThat(capturedBallRecord.getScore() == score.getScore());

        verify(gameRepository).update(gameRecordArgumentCaptor.capture());
        GameRecord capturedGameRecord = gameRecordArgumentCaptor.getValue();
        assertThat(capturedGameRecord.getTeam1Score()).isEqualTo(5);



    }

    @Test
    public void shouldCallOverSaveOnlyOnceForSameOverMultipleBalls(){

        ScoreService scoreService = new ScoreService(ballRepository,overRepository,
                gameRepository,playerRepository);

        UUID gameId = UUID.randomUUID();
        UUID overId = UUID.randomUUID();
        UUID batsmanOnstrikeId = UUID.randomUUID();
        UUID nonSrikerId = UUID.randomUUID();



        GameRecord game = getGameRecord(gameId);

        PlayersRecord batsmanOnStrike = new PlayersRecord(batsmanOnstrikeId, "Test Batsman", "Team 1");
        PlayersRecord nonStriker = new PlayersRecord(nonSrikerId, "Test Batsman2", "Team 1");

        Score score = new Score(Arrays.asList(new Batsman(batsmanOnstrikeId,batsmanOnStrike.getName(),true),
                new Batsman(nonSrikerId,nonStriker.getName(),false)),
                5, 4, gameId);


        OverRecord over = new OverRecord(overId, score.getOver(), batsmanOnStrike.getTeamName(), gameId);


        Optional<OverRecord> overRecord = Optional.of(over);

        when(playerRepository.getPlayer(batsmanOnstrikeId)).thenReturn(batsmanOnStrike);
        when(overRepository.getOverDetails(score.getOver(), batsmanOnStrike.getTeamName()))
                .thenReturn(Optional.empty())
                .thenReturn(overRecord);
        when(gameRepository.getGameInformationForId(gameId)).thenReturn(game);

        scoreService.addScore(score);

        verify(overRepository,times(1)).save(overRecordArgumentCaptor.capture());
        OverRecord capturedOverRecord = overRecordArgumentCaptor.getValue();
        assertThat(capturedOverRecord.getNumber()).isEqualTo(over.getNumber());


    }

    private GameRecord getGameRecord(UUID gameId) {
        GameRecord game = new GameRecord();
        game.setId(gameId);
        game.setTeam1("Team 1");
        game.setTeam2("Team 2");
        game.setTeam1Score(0);
        game.setTeam2Score(0);
        return game;
    }

}