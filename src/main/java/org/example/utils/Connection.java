package org.example.utils;

public class Connection {
    private final int connectionId;

    public Connection(int connectionId) {
        this.connectionId = connectionId;
    }

    public int getConnectionId() {
        return connectionId;
    }

    @Override
    public String toString() {
        return "Connection #" + getConnectionId();
    }
}