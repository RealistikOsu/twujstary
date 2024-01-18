package com.realistikosu.bancho.web;

import spark.Request;
import spark.Response;

public class BanchoHandler {
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
        return "HELLO!!!";
    }
}
