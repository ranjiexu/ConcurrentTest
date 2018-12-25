package chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xuran on 2018/12/21.
 * 同步屏障 CyclicBarrier 代码演示
 */
public class CyclicBarrierTest {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " before barrier");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " after barrier");

            }
        }).start();

        System.out.println(Thread.currentThread().getName() + " before barrier");
        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + " after barrier");
    }
}
