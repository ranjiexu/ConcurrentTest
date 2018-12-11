package chapter4;

import util.SleepUtils;

/**
 * Created by xuran on 2018/12/11.
 * 安全中止线程的两种方式，interrupt 和 volatile boolean 标识位
 * 这两种方式都能使线程有机会清理资源，而不是武断的将线程终止
 */
public class Shutdown {

    public static void main(String[] args) {
        // 中断线程
        Thread countThread = new Thread(new Runner(), "CountThread");
        countThread.start();
        SleepUtils.second(5);
        countThread.interrupt();
        // 通过 volatile 标识位控制线程
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        SleepUtils.second(5);
        two.cancel();
    }

    static class Runner implements Runnable {
        private long i = 0;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel () {
            on = false;
        }
    }
}
