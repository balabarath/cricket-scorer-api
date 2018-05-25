package com.tw.cricketScorer.game;

import com.tw.cricketScorer.game.score.BallRepository;
import com.tw.cricketScorer.player.Player;
import com.tw.cricketScorer.player.PlayerRepository;
import cricketScorer.db.gen.tables.records.BallRecord;
import cricketScorer.db.gen.tables.records.GameRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {


    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private BallRepository ballRepository;

    @Mock
    private GameRecord gameRecord;

    @Autowired
    DSLContext dsl;

    @Mock
    private  Result<BallRecord> ballRecords;

    @Test
    public void shouldReturnGameData(){
        var newplayyer1UUID = new UUID(1234,0);
        var newplayyer2UUID = new UUID(5678,0);
        when(gameRecord.getTeam1()).thenReturn("Team 1");
        when(gameRecord.getTeam2()).thenReturn("Team 2");
        when(gameRecord.getTeam1Overs()).thenReturn(new BigDecimal(10.4f));
        when(gameRecord.getTeam2Overs()).thenReturn(new BigDecimal(20.0f));
        when(gameRecord.getTeam1Score()).thenReturn(0);
        when(gameRecord.getTeam2Score()).thenReturn(0);
        when(gameRecord.getTeam1Wickets()).thenReturn(0);
        when(gameRecord.getTeam2Wickets()).thenReturn(0);
        when(gameRepository.getGameInformation()).thenReturn(gameRecord);
        when(playerRepository.getPlayers("Team 1")).thenReturn(Arrays.asList(new Player(newplayyer1UUID,"Sarang")
                                                                                       ,new Player(UUID.randomUUID(),"Charu")));
        when(playerRepository.getPlayers("Team 2")).thenReturn(Arrays.asList(new Player(newplayyer2UUID,"Sethu"),
                                                                                      new Player(UUID.randomUUID(),"Bala")));

        var service = new GameService(gameRepository,playerRepository,ballRepository);
        var game = service.getGameRecord();
        assertEquals("Team 1",game.getTeams().get(0).getName());
        assertEquals("Team 2",game.getTeams().get(1).getName());


    }


    @Test
    public void shouldReturnBatingTeamPlayersScoreDetails(){

        UUID playerId = UUID.randomUUID();
        List<Player> playersForBattingTeam = new ArrayList<>();
        Player player = new Player(playerId,"Test Batsman");
        playersForBattingTeam.add(player);

        List<BallRecord> ballRecordList = createBallRecords(playerId);

        var gameId = UUID.randomUUID();
        GameService gameService = new GameService(gameRepository,playerRepository,ballRepository);

        BatsmanDetails batsmanDetails = new BatsmanDetails(playerId,"Test Name",10,2, 1, 1, "500");

        when(gameRepository.getCurrentBattingTeam(gameId)).thenReturn("Team 1");
        when(ballRepository.getPlayedBalls("Team 1")).thenReturn(ballRecordList);
        when(playerRepository.getPlayers("Team 1")).thenReturn(playersForBattingTeam);

        List<BatsmanDetails> team1BatsmanDetails = gameService.getBattingTeamScoreDetails(gameId);

        assertEquals(batsmanDetails.getStrikeRate(), team1BatsmanDetails.get(0).getStrikeRate());

    }

    private List<BallRecord> createBallRecords(UUID playerId){
        List<BallRecord> ballRecordList = new ArrayList<>();
        BallRecord ballRecord1 = new BallRecord();
        ballRecord1.setId(UUID.randomUUID());
        ballRecord1.setScore(4);
        ballRecord1.setTeamName("Team 1");
        ballRecord1.setBatsmanId(playerId);
        ballRecordList.add(ballRecord1);


        BallRecord ballRecord2 = new BallRecord();
        ballRecord2.setId(UUID.randomUUID());
        ballRecord2.setScore(6);
        ballRecord2.setTeamName("Team 1");
        ballRecord2.setBatsmanId(playerId);
        ballRecordList.add(ballRecord2);

        return ballRecordList;
    }

}