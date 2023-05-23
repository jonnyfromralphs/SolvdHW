package org.example;

public class ThreadMain {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread number: " + Thread.currentThread().getId());
                    for(int i = 0; i < 5; i++) {
                        System.out.print(".");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("100%\n");
                System.out.println("Loading complete");

            }
        };

        Thread loadingThread = new Thread(runnable);
        loadingThread.start();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread number: " + Thread.currentThread().getId());
        });

        thread.start();

        System.out.println("Main thread: " + Thread.currentThread().getId());
    }
}
