package chapter4;

import util.SleepUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuran on 2018/12/13.
 * 等待/通知机制
 * 经典范式
 *
 * synchronized (对象) {
 *     while (条件不满足) {
 *         对象.wait();
 *     }
 * }
 * 对应的处理逻辑
 *
 * synchronized (对象) {
 *     改变条件
 *     对象.notifyAll();
 * }
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {
        Thread wait = new Thread(new Wait(), "WaitThread");
        wait.start();
        SleepUtils.second(1);
        Thread notify = new Thread(new Notify(), "NotifyThread");
        notify.start();
    }

    static class Wait implements Runnable {
        DateFormat formatData = new SimpleDateFormat("HH:mm:ss");
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    System.out.println(Thread.currentThread().getName() + " wait @ " + formatData.format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + " rerun @ " + formatData.format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold @ " + dateFormat.format(new Date()));
                flag = false;
                lock.notifyAll();
                SleepUtils.second(5);
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " locked @ " + dateFormat.format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
