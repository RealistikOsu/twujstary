package com.realistikosu.bancho.web;

import com.realistikosu.bancho.protocol.structures.ServerNotification;
import com.realistikosu.bancho.protocol.structures.ServerRestartNotify;
import com.realistikosu.resources.users.UserRepository;
import spark.Request;
import spark.Response;
import com.zaxxer.hikari.HikariDataSource;

import com.realistikosu.bancho.sessions.SessionManager;
import com.realistikosu.bancho.sessions.Session;
import com.realistikosu.bancho.protocol.structures.SerialisablePacket;
import com.realistikosu.bancho.protocol.structures.ServerLoginReply;
import com.realistikosu.bancho.protocol.io.Writer;
import com.realistikosu.osu.PacketId;
import com.realistikosu.resources.stats.StatsRepository;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
            ServerNotification serverNotification = new ServerNotification("Skill issue???");
            ServerLoginReply serverLoginReply = ServerLoginReply.loginFailed();
            try {
                Writer writer = serverNotification.write();
                serverLoginReply.write(writer);
                response.raw().getOutputStream().write(writer.bytes());
            } catch (IOException e) {
                return "AAA AA";
            }

            return null;
        }

        Session session = _sessionManager.getById(token);

        if (session == null) {
            try {
                byte[] res = new ServerRestartNotify(0).write().bytes();
                response.raw().getOutputStream().write(res);
                return null;
            } catch (IOException e) {
                return "LOL";
            }

        }

        // Context creation
        Connection connection;
        try {
            connection = this._mySqlSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        StatsRepository statsRepository = new StatsRepository(connection);
        UserRepository userRepository = new UserRepository();

        BanchoContext banchoContext = new BanchoContext(statsRepository, userRepository, session);

        // Find the handler
        try {
            // TODO: Logic.
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

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
