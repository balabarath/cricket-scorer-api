package com.tw.cricketScorer.game.score;

import com.tw.cricketScorer.game.GameRepository;
import com.tw.cricketScorer.game.score.BallRepository;
import com.tw.cricketScorer.game.score.OverRepository;
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
}