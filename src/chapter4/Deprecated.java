package chapter4;

import util.SleepUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuran on 2018/12/11.
 * 演示线程暂停、恢复、停止的效果
 * 此三个方法都被废弃，原因是资源会被占用引起死锁
 * 可以用等待/通知方法来替代
 */
public class Deprecated {
    public static void main(String[] args) throws InterruptedException {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Thread thread = new Thread(new PrintRunner(), "PrintThread");
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        // 主线程暂停打印线程
        thread.suspend();
        System.out.println(Thread.currentThread().getName() + " supend printThread at " + dateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        // 主线程恢复打印线程
        thread.resume();
        System.out.println(Thread.currentThread().getName() + "resume printThread at " + dateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        // 主线程停止打印线程
        thread.stop();
        System.out.println(Thread.currentThread().getName() + " stop printThread at " + dateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }

    static class PrintRunner implements Runnable {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " run at " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
