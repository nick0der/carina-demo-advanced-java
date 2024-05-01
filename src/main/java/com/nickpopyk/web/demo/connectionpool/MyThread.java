package com.nickpopyk.web.demo.connectionpool;

import static com.nickpopyk.web.demo.connectionpool.ConnectionProcessor.processConnection;

import java.util.Queue;

public class MyThread extends Thread {
    private Queue<Connection> pool;

    public MyThread(Queue<Connection> pool) {
        this.pool = pool;
    }

    public void run() {
        processConnection(pool, 2000);
    }
}
