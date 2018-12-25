package chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xuran on 2018/12/21.
 * 同步屏障 状态获取 相对于 CountDownLatch 有更多的方法获取状态
 */
public class CyclicBarrierTest3 {
    static CyclicBarrier barrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread.interrupt();

        try {
            barrier.await();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: " + barrier.isBroken());
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println("BrokenBarrierException: " + barrier.isBroken());
            e.printStackTrace();
        }
    }
}
