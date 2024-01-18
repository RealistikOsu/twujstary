package com.realistikosu.bancho.web;

import java.io.DataInput;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BanchoController {
    // The "osu!" client will always send a POST request.
    @PostMapping("/")
    public String handleBanchoRequest(
            @RequestHeader(name="User-Agent")
            String userAgent,
            @RequestHeader(name="osu-token", required = false)
            String sessionId,
            @RequestBody
            DataInput bodyStream
    ) {
        if (!userAgent.equals("osu!")) {
            return "LOL!";
        }

        return "hi osu!";
    }

    // Likely a browser or a bot scraping our main page.
    @GetMapping("/")
    public String mainHandler() {
        return "HELLO!!!";
    }
}
