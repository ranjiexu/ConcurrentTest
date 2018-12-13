package chapter4;

import util.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuran on 2018/12/13.
 * 线程join()方法演示
 * 如果线程A执行了thread.join()方法，其含义是：线程A等待thread终止之后，从thread.join()方法返回继续执行
 */
public class Join {
    public static void main(String[] args) {
        Thread preThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " start @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domin(preThread), "Thread" + i);
            thread.start();
            preThread = thread;
        }
        SleepUtils.second(5);
        System.out.println(Thread.currentThread().getName() + " terminal @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }

    static class Domin implements Runnable {
        Thread thread;

        public Domin(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SleepUtils.second(1);
            System.out.println(Thread.currentThread().getName() + " terminal @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }
}
