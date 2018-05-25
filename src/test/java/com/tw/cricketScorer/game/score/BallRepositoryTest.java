package com.tw.cricketScorer.game.score;

import com.tw.cricketScorer.game.GameRepository;
import cricketScorer.db.gen.tables.records.BallRecord;
import cricketScorer.db.gen.tables.records.GameRecord;
import cricketScorer.db.gen.tables.records.OverRecord;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static cricketScorer.db.gen.tables.Ball.BALL;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class BallRepositoryTest {

    @Autowired
    private BallRepository ballRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private OverRepository overRepository;

    @Autowired
    private DSLContext dslContext;

    @Test
    public void shouldBeAbleToSaveBallInformation() {

        GameRecord game = gameRepository.getGameInformation();

        OverRecord overRecord = new OverRecord();
        overRecord.setNumber(1);
        overRecord.setTeamName(game.getTeam1());
        overRecord.setGame(game.getId());
        overRecord.setId(UUID.randomUUID());
        overRepository.save(overRecord);


        OverRecord over = overRepository.getOverDetails(1, game.getTeam1()).get();
        BallRecord ball = new BallRecord();

        ball.setTeamName(game.getTeam1());
        UUID batsmanId = UUID.fromString("16a2a274-5bdb-11e8-9c2d-fa7ae01bbebc");
        ball.setBatsmanId(batsmanId);
        ball.setOverId(over.getId());
        ball.setScore(6);

        ballRepository.save(ball);

        BallRecord retRecord = dslContext.selectFrom(BALL).fetchOne();
        assertEquals(batsmanId, retRecord.getBatsmanId());
        assertEquals(over.getId(), retRecord.getOverId());

    }


    @Test
    public void shouldReturnCurrentlyPlayingAndAlreadyOutPlayersFromGivenTeam(){

        UUID overId = UUID.fromString("16a2a274-5bdb-11e8-9c2d-fa7ae01bbebd");
        saveOverDetails(1, "Team 1", overId);
        OverRecord over = overRepository.getOverDetails(1, "Team 1").get();
        BallRecord ball = new BallRecord();

        //TODO : CHECK WHY IT GOT FAILED FOR TWO BALLS

        UUID firstBatsmanId = UUID.fromString("16a2a274-5bdb-11e8-9c2d-fa7ae01bbebc");
        UUID secondBatsmanId = UUID.fromString("16a2a62a-5bdb-11e8-9c2d-fa7ae01bbebc");

        BallRecord firstBall = createBallRecord("Team 1", firstBatsmanId, overId, 10);
        BallRecord secondBall = createBallRecord("Team 1", secondBatsmanId, overId, 10);

        ballRepository.save(firstBall);
       ballRepository.save(secondBall);

        List<BallRecord> currentlyPlayingPlayers = ballRepository.getPlayedBalls("Team 1");

        assertEquals(firstBall.getBatsmanId(),    currentlyPlayingPlayers.get(0).getBatsmanId());
       assertEquals(secondBall.getBatsmanId(),    currentlyPlayingPlayers.get(1).getBatsmanId());

    }

    private void saveOverDetails(int overNumber,String teamName,UUID overId) {

        GameRecord game = gameRepository.getGameInformation();

        OverRecord overRecord = new OverRecord();
        overRecord.setNumber(overNumber);
        overRecord.setTeamName(teamName);
        overRecord.setGame(game.getId());
        overRecord.setId(overId);
        overRepository.save(overRecord);

    }

    private BallRecord createBallRecord(String teamName, UUID batsmanId, UUID overId, int score) {

        BallRecord ballRecord = new BallRecord();
        ballRecord.setTeamName(teamName);
        ballRecord.setBatsmanId(batsmanId);
        ballRecord.setOverId(overId);
        ballRecord.setScore(score);
        return ballRecord;

    }
}