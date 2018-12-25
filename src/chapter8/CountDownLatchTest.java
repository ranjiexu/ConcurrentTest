package chapter8;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xuran on 2018/12/21.
 * 等待多线程完成 CountDownLatch 代码演示
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start run");
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " count down 1 -");
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " count down 1 --");
            }
        });
        thread.start();

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " finish");
    }
}
