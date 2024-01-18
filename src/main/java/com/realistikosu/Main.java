package com.realistikosu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.realistikosu.bancho.web.BanchoController;

@SpringBootApplication
public class Main {
    private static final Logger logger = LogManager.getLogger("HelloWorld");

    public static void main(String[] args) {
        logger.error("Hiya!");
        SpringApplication.run(BanchoController.class);
    }
}