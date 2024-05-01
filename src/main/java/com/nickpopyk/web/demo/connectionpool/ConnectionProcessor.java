package com.nickpopyk.web.demo.connectionpool;

import java.lang.invoke.MethodHandles;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static void processConnection(Queue<Connection> pool, long delay) {
        Connection connection;

        try {
            Thread.sleep((long) (Math.random() * delay));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (pool) {
            while (pool.isEmpty()) {
                try {
                    LOGGER.info("Waiting for a connection...");
                    pool.wait(); // Wait for notification from other threads when a connection is available
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            connection = pool.poll();
        }

        // Simulating some work with the connection
        try {
            connection.connect();
            Thread.sleep(5000);
            connection.disconnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Release the connection back to the pool
        synchronized (pool) {
            pool.offer(connection);
            pool.notify(); // Notify other waiting threads that a connection is available
        }
    }
}
