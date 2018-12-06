package chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xuran on 2018/12/6.
 * 多线程计数器
 * 普通自增和原子自增的比较
 */
public class Counter {
    private AtomicInteger integer = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Counter counter = new Counter();
        List<Thread> threadList = new ArrayList<>(200);

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        counter.count();
                        counter.atomicCount();
                    }
                }
            });
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("atomicInteger:" + counter.integer.get());
        System.out.println("i:" + counter.i);
        System.out.println("time:" + (System.currentTimeMillis() - start));
    }

    private void atomicCount() {
        for (;;) {
            int i = integer.get();
            boolean suc = integer.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }
}
