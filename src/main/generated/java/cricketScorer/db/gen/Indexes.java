/*
 * This file is generated by jOOQ.
*/
package cricketScorer.db.gen;


import cricketScorer.db.gen.tables.Ball;
import cricketScorer.db.gen.tables.Game;
import cricketScorer.db.gen.tables.Over;
import cricketScorer.db.gen.tables.Players;
import cricketScorer.db.gen.tables.SchemaVersion;
import cricketScorer.db.gen.tables.Team;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index CONSTRAINT_INDEX_1 = Indexes0.CONSTRAINT_INDEX_1;
    public static final Index CONSTRAINT_INDEX_1E = Indexes0.CONSTRAINT_INDEX_1E;
    public static final Index CONSTRAINT_INDEX_1EF = Indexes0.CONSTRAINT_INDEX_1EF;
    public static final Index PRIMARY_KEY_1 = Indexes0.PRIMARY_KEY_1;
    public static final Index CONSTRAINT_INDEX_2 = Indexes0.CONSTRAINT_INDEX_2;
    public static final Index CONSTRAINT_INDEX_21 = Indexes0.CONSTRAINT_INDEX_21;
    public static final Index PRIMARY_KEY_21 = Indexes0.PRIMARY_KEY_21;
    public static final Index CONSTRAINT_INDEX_25 = Indexes0.CONSTRAINT_INDEX_25;
    public static final Index CONSTRAINT_INDEX_253 = Indexes0.CONSTRAINT_INDEX_253;
    public static final Index PRIMARY_KEY_25 = Indexes0.PRIMARY_KEY_25;
    public static final Index CONSTRAINT_INDEX_D = Indexes0.CONSTRAINT_INDEX_D;
    public static final Index PRIMARY_KEY_D = Indexes0.PRIMARY_KEY_D;
    public static final Index PRIMARY_KEY_2 = Indexes0.PRIMARY_KEY_2;
    public static final Index PRIMARY_KEY_6 = Indexes0.PRIMARY_KEY_6;
    public static final Index SCHEMA_VERSION_S_IDX = Indexes0.SCHEMA_VERSION_S_IDX;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 extends AbstractKeys {
        public static Index CONSTRAINT_INDEX_1 = createIndex("CONSTRAINT_INDEX_1", Ball.BALL, new OrderField[] { Ball.BALL.TEAM_NAME }, false);
        public static Index CONSTRAINT_INDEX_1E = createIndex("CONSTRAINT_INDEX_1E", Ball.BALL, new OrderField[] { Ball.BALL.OVER_ID }, false);
        public static Index CONSTRAINT_INDEX_1EF = createIndex("CONSTRAINT_INDEX_1EF", Ball.BALL, new OrderField[] { Ball.BALL.BATSMAN_ID }, false);
        public static Index PRIMARY_KEY_1 = createIndex("PRIMARY_KEY_1", Ball.BALL, new OrderField[] { Ball.BALL.ID }, true);
        public static Index CONSTRAINT_INDEX_2 = createIndex("CONSTRAINT_INDEX_2", Game.GAME, new OrderField[] { Game.GAME.TEAM1 }, false);
        public static Index CONSTRAINT_INDEX_21 = createIndex("CONSTRAINT_INDEX_21", Game.GAME, new OrderField[] { Game.GAME.TEAM2 }, false);
        public static Index PRIMARY_KEY_21 = createIndex("PRIMARY_KEY_21", Game.GAME, new OrderField[] { Game.GAME.ID }, true);
        public static Index CONSTRAINT_INDEX_25 = createIndex("CONSTRAINT_INDEX_25", Over.OVER, new OrderField[] { Over.OVER.TEAM_NAME }, false);
        public static Index CONSTRAINT_INDEX_253 = createIndex("CONSTRAINT_INDEX_253", Over.OVER, new OrderField[] { Over.OVER.GAME }, false);
        public static Index PRIMARY_KEY_25 = createIndex("PRIMARY_KEY_25", Over.OVER, new OrderField[] { Over.OVER.ID }, true);
        public static Index CONSTRAINT_INDEX_D = createIndex("CONSTRAINT_INDEX_D", Players.PLAYERS, new OrderField[] { Players.PLAYERS.TEAM_NAME }, false);
        public static Index PRIMARY_KEY_D = createIndex("PRIMARY_KEY_D", Players.PLAYERS, new OrderField[] { Players.PLAYERS.ID }, true);
        public static Index PRIMARY_KEY_2 = createIndex("PRIMARY_KEY_2", Team.TEAM, new OrderField[] { Team.TEAM.TEAM_NAME }, true);
        public static Index PRIMARY_KEY_6 = createIndex("PRIMARY_KEY_6", SchemaVersion.SCHEMA_VERSION, new OrderField[] { SchemaVersion.SCHEMA_VERSION.INSTALLED_RANK }, true);
        public static Index SCHEMA_VERSION_S_IDX = createIndex("schema_version_s_idx", SchemaVersion.SCHEMA_VERSION, new OrderField[] { SchemaVersion.SCHEMA_VERSION.SUCCESS }, false);
    }
}
