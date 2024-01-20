package com.realistikosu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Spark;

import com.realistikosu.bancho.web.BanchoHandler;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String configPath = "config.ini";

    public static void main(String[] args) {
        logger.info("twujstary is starting...");
        logger.info("Loading config from '{}'", configPath);

        Properties configReader = new Properties();

        try {
            FileInputStream configFile = new FileInputStream(configPath);
            configReader.load(configFile);
        } catch (FileNotFoundException e) {
            logger.fatal("Could not find a config file. Please ensure one is present.", e);
            System.exit(1);
        }
        catch (Exception e) {
            logger.fatal("Could not read config.", e);
        }

        // Fetching config variables.
        int configHttpPort = Integer.parseInt(configReader.getProperty("HTTP_PORT"));

        BanchoHandler banchoHandler = new BanchoHandler();

        logger.info("Starting server on port {}", configHttpPort);
        Spark.port(configHttpPort);

        Spark.get("/", banchoHandler::mainHandlerGet);
        Spark.post("/", banchoHandler::mainHandlerPost);

    }
}