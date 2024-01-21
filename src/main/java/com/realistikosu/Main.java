package com.realistikosu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Spark;
import com.zaxxer.hikari.HikariConfig;

import com.realistikosu.bancho.web.BanchoHandler;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
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
            System.exit(1);
        }

        // MySQL Connection handling.
        String mySQLUser = configReader.getProperty("MYSQL_USER");
        String mySQLPassword = configReader.getProperty("MYSQL_PASSWORD");
        String mySQLDatabase = configReader.getProperty("MYSQL_DATABASE");
        int mySQLPort = Integer.parseInt(configReader.getProperty("MYSQL_PORT"));
        String mySQLHost = configReader.getProperty("MYSQL_HOST");

        // TODO: HikariConfig supports reading directly from `Properties`.
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(
                String.format(
                        "jdbc:mysql://%s:%s/%s",
                        mySQLHost,
                        mySQLPort,
                        mySQLDatabase
                )
        );
        hikariConfig.setUsername(mySQLUser);
        hikariConfig.setPassword(mySQLPassword);
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        HikariDataSource hikariPool = new HikariDataSource(hikariConfig);


        BanchoHandler banchoHandler = new BanchoHandler(
                hikariPool
        );

        // HTTP Server Configuration
        int configHttpPort = Integer.parseInt(configReader.getProperty("HTTP_PORT"));

        logger.info("Starting server on port {}", configHttpPort);
        Spark.port(configHttpPort);

        Spark.get("/", banchoHandler::mainHandlerGet);
        Spark.post("/", banchoHandler::mainHandlerPost);

    }
}