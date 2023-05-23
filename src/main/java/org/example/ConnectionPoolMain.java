package org.example;

import org.example.utils.Connection;
import org.example.utils.ConnectionPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolMain {
    public static void main(String[] args) {
        final int POOL_SIZE = 5;
        int threadCount = 7;

        ConnectionPool connectionPool = new ConnectionPool(POOL_SIZE);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                Connection connection = null;
                try {
                    connection = connectionPool.getConnection();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Single Thread: Got connection with thread #" + Thread.currentThread().getId());

                try {
                    Thread.sleep((int) (Math.random() * 3000));
                    System.out.println("Single Thread: Released connection #" + connection.getConnectionId());
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        for (int i = 0; i < (threadCount - POOL_SIZE); i++) {
            executorService.execute(() -> {
                Connection connection = null;

                try {
                    connection = connectionPool.getConnection();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Thread Pool: Got connection with thread #" + Thread.currentThread().getId());

                try {
                    Thread.sleep((int) (Math.random() * 3000));
                    System.out.println("Thread Pool: Released connection #" + connection.getConnectionId());
                    connectionPool.releaseConnection(connection);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        executorService.shutdown();
    }
}
