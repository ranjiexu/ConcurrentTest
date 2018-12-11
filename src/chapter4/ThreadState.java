package chapter4;

import util.SleepUtils;

/**
 * Created by xuran on 2018/12/11.
 * 用命令查看进程状态
 * jps寻找进程id
 * jstack查看进程详细信息
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "Thread-TimeWaiting").start();
        new Thread(new Waiting(), "Thread-Waiting").start();
        new Thread(new Blocked(), "Thread-Blocked-1").start();
        new Thread(new Blocked(), "Thread-Blocked-2").start();
    }

    // 该线程不断的进行睡眠
    static class TimeWaiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    // 该线程在Waiting.class实例上等待
    static class Waiting implements Runnable {

        @Override
        public void run() {
            synchronized (Waiting.class) {
                try {
                    Waiting.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }
}
