package chapter8;

import util.SleepUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xuran on 2018/12/25.
 * 信号量 Samephore 代码演示
 */
public class SemaphoreTest {
    private static final int THREAD_NUMBER = 30;

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_NUMBER);

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " save data, available : "
                            + semaphore.availablePermits() + " , length : " + semaphore.getQueueLength());
                    SleepUtils.second(5);
                    semaphore.release();
                }
            });
        }
        executor.shutdown();
    }
}
