package com.realistikosu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import spark.Spark;

import com.realistikosu.bancho.web.BanchoHandler;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.error("Hiya!");

        Spark.port(80);

        BanchoHandler banchoHandler = new BanchoHandler();

        Spark.get("/", banchoHandler::mainHandlerGet);
        Spark.post("/", banchoHandler::mainHandlerPost);

    }
}