package com.tw.cricketScorer.game.score;

import com.tw.cricketScorer.game.GameRepository;
import cricketScorer.db.gen.tables.records.BallRecord;
import cricketScorer.db.gen.tables.records.GameRecord;
import cricketScorer.db.gen.tables.records.OverRecord;
import org.assertj.core.api.Assertions;
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
        UUID overId = UUID.randomUUID();
        OverRecord over = createOverRecord(1,game.getTeam1(),overId);
        overRepository.save(over);
        OverRecord fetchedOver = overRepository.getOverDetails(1, game.getTeam1()).get();
        UUID batsmanId = UUID.fromString("16a2a274-5bdb-11e8-9c2d-fa7ae01bbebc");
        BallRecord ball = createBallRecord(game.getTeam1(),batsmanId,overId,6);
        ballRepository.save(ball);

        BallRecord retRecord = dslContext.selectFrom(BALL).fetchOne();
        assertEquals(batsmanId, retRecord.getBatsmanId());
        assertEquals(fetchedOver.getId(), retRecord.getOverId());

    }


    @Test
    public void shouldReturnCurrentlyPlayingAndAlreadyOutPlayersFromGivenTeam(){

        UUID overId = UUID.fromString("16a2a274-5bdb-11e8-9c2d-fa7ae01bbebd");
        UUID firstBatsmanId = UUID.fromString("16a2a274-5bdb-11e8-9c2d-fa7ae01bbebc");
        UUID secondBatsmanId = UUID.fromString("16a2a62a-5bdb-11e8-9c2d-fa7ae01bbebc");
        OverRecord over = createOverRecord(1, "Team 1", overId);
        overRepository.save(over);
        BallRecord firstBall = createBallRecord("Team 1", firstBatsmanId, overId, 10);
        BallRecord secondBall = createBallRecord("Team 1", secondBatsmanId, overId, 10);
        ballRepository.save(firstBall);
        ballRepository.save(secondBall);

        List<BallRecord> currentlyPlayedBalls = ballRepository.getPlayedBalls("Team 1");

        Assertions.assertThat(firstBall.getBatsmanId()).isEqualTo(currentlyPlayedBalls.get(0).getBatsmanId());

        Assertions.assertThat(secondBall.getBatsmanId()).isEqualTo(currentlyPlayedBalls.get(1).getBatsmanId());

        assertEquals(firstBall.getBatsmanId(),    currentlyPlayedBalls.get(0).getBatsmanId());
        assertEquals(firstBall.getTeamName(),    currentlyPlayedBalls.get(0).getTeamName());
        assertEquals(secondBall.getBatsmanId(),    currentlyPlayedBalls.get(1).getBatsmanId());
        assertEquals(secondBall.getTeamName(),    currentlyPlayedBalls.get(1).getTeamName());

    }

    private OverRecord createOverRecord(int overNumber, String teamName, UUID overId) {

        GameRecord game = gameRepository.getGameInformation();

        OverRecord overRecord = new OverRecord();
        overRecord.setNumber(overNumber);
        overRecord.setTeamName(teamName);
        overRecord.setGame(game.getId());
        overRecord.setId(overId);
        return overRecord;
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