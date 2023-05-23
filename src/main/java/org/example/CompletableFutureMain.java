package org.example;

import org.example.utils.Connection;
import org.example.utils.ConnectionPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int POOL_SIZE = 5;
        int threadCount = 7;

        ConnectionPool connectionPool = new ConnectionPool(POOL_SIZE);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
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
            }, executorService);
            futures.add(future);
        }

        for (int i = 0; i < (threadCount - POOL_SIZE); i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
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
            }, executorService);
            futures.add(future);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allFutures.get();

        executorService.shutdown();
    }
}