package com.nickpopyk.web.demo.connectionpool;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connection {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private int id;

    public Connection(int id) {
        this.id = id;
    }

    public void connect() {
        LOGGER.info("Connection " + id + " established.");
    }

    public void disconnect() {
        LOGGER.info("Connection " + id + " closed.");
    }
}
