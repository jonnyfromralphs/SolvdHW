package org.example.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private final BlockingQueue<Connection> pool;

    private int connectionId = 0;

    public ConnectionPool(int size) {
        this.pool = new LinkedBlockingQueue<>(size);
        initializeConnections(size);

    }

    private void initializeConnections(int size) {
        for (int i = 0; i < size; i++) {
            Connection connection = new Connection(connectionId);
            pool.offer(connection);
            incrementId();
        }
    }

    public int getSize() {
        return pool.size();
    }

    public void incrementId() {
        connectionId += 1;
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(Connection connection) {
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
