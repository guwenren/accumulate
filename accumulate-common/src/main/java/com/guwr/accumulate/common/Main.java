package com.guwr.accumulate.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.common.Main
 * Date         2016/11/17
 * Time         9:39
 * Description
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.trace("======trace");
        logger.debug("======debug");
        logger.info("======info");
        logger.warn("======warn");
        logger.error("======error");
    }
}
