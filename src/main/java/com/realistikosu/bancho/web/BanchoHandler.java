package com.realistikosu.bancho.web;

import spark.Request;
import spark.Response;
import com.zaxxer.hikari.HikariDataSource;

import com.realistikosu.bancho.sessions.SessionManager;

/**
 * The main entrypoint and state manager for 
 */
public class BanchoHandler {
    private final SessionManager _sessionManager;
    private final HikariDataSource _mySqlSource;


    public BanchoHandler(HikariDataSource hikariDataSource) {
        _sessionManager = new SessionManager();
        _mySqlSource = hikariDataSource;
    }


    // The "osu!" client will always send a POST request.
    public String mainHandlerPost(
            Request request,
            Response response
    ) {

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
