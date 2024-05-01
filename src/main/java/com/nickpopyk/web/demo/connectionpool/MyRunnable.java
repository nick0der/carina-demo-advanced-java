package com.nickpopyk.web.demo.connectionpool;

import static com.nickpopyk.web.demo.connectionpool.ConnectionProcessor.processConnection;

import java.util.Queue;

public class MyRunnable implements Runnable {
    private Queue<Connection> pool;

    public MyRunnable(Queue<Connection> pool) {
        this.pool = pool;
    }

    public void run() {
        processConnection(pool, 1000);
    }
}
