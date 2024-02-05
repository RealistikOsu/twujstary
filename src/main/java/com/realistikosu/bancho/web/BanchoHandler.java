package com.realistikosu.bancho.web;

import spark.Request;
import spark.Response;
import com.zaxxer.hikari.HikariDataSource;

import com.realistikosu.bancho.sessions.SessionManager;
import com.realistikosu.bancho.sessions.Session;
import com.realistikosu.bancho.protocol.structures.SerialisablePacket;
import com.realistikosu.bancho.protocol.structures.ServerLoginReply;
import com.realistikosu.osu.PacketId;
import com.realistikosu.resources.stats.StatsRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * The main entrypoint and state manager for 
 */
public class BanchoHandler {
    private final SessionManager _sessionManager;
    private final DataSource _mySqlSource;
    private final HashMap<PacketId, SerialisablePacket> packetHashMap = new HashMap<>() {{
        put(PacketId.SRV_LOGIN_REPLY, new ServerLoginReply());

    }};


    public BanchoHandler(DataSource hikariDataSource) {
        _sessionManager = new SessionManager();
        _mySqlSource = hikariDataSource;

    }


    // The "osu!" client will always send a POST request.
    public String mainHandlerPost(
            Request request,
            Response response
    ) {
        String token = request.headers("osu-token");

        if (token == null) {
            // Login logic.
            return "L";
        }

        Session session = _sessionManager.getById(token);

        if (session == null) {
            // Get a re-log.

        }

        // TODO: Do transactions.
        Connection connection;
        try {
            connection = this._mySqlSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Init repos.
        StatsRepository statsRepository = new StatsRepository(connection);

        BanchoContext banchoContext = new BanchoContext(statsRepository);

        // Find the handler

        return "hi osu!";
    }

    // Likely a browser or a bot scraping our main page.
    public String mainHandlerGet(
            Request request,
            Response response
    ) {
        return "Harry piję wódkę!!!";
    }
}
