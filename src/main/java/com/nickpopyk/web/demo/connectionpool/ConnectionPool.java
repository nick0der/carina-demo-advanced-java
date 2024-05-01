package com.nickpopyk.web.demo.connectionpool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private static Queue<Connection> pool;

    private ConnectionPool() {}

    public static Queue<Connection> getInstance(int amountOfConnections) {
        if (pool == null) {
            synchronized (ConnectionPool.class) {
                if (pool == null) {
                    pool = new ConcurrentLinkedQueue<>();
                    for (int i = 0; i < amountOfConnections; i++) {
                        pool.add(new Connection(i));
                    }
                }
            }
        }
        return pool;
    }
}
