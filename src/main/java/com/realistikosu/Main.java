package com.realistikosu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger("HelloWorld");

    public static void main(String[] args) {
        logger.error("Hiya!");
        final int a = 2;
    }
}