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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1257937003;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.BALL</code>.
     */
    public final Ball BALL = cricketScorer.db.gen.tables.Ball.BALL;

    /**
     * The table <code>public.GAME</code>.
     */
    public final Game GAME = cricketScorer.db.gen.tables.Game.GAME;

    /**
     * The table <code>public.OVER</code>.
     */
    public final Over OVER = cricketScorer.db.gen.tables.Over.OVER;

    /**
     * The table <code>public.PLAYERS</code>.
     */
    public final Players PLAYERS = cricketScorer.db.gen.tables.Players.PLAYERS;

    /**
     * The table <code>public.TEAM</code>.
     */
    public final Team TEAM = cricketScorer.db.gen.tables.Team.TEAM;

    /**
     * The table <code>public.schema_version</code>.
     */
    public final SchemaVersion SCHEMA_VERSION = cricketScorer.db.gen.tables.SchemaVersion.SCHEMA_VERSION;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Ball.BALL,
            Game.GAME,
            Over.OVER,
            Players.PLAYERS,
            Team.TEAM,
            SchemaVersion.SCHEMA_VERSION);
    }
}
