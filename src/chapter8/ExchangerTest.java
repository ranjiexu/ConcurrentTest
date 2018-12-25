package chapter8;

import util.SleepUtils;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by xuran on 2018/12/25.
 * 线程间交换数据 Exchanger 代码演示
 */
public class ExchangerTest {
    private static Exchanger<String> exchanger = new Exchanger<String>();
    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String A = "A";
                String B = null;
                SleepUtils.second(10);
                try {
                    B = exchanger.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " exchanger result : " + B);
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String B = "B";
                String A = null;
                try {
                    A = exchanger.exchange(B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " exchanger result : " + A);
            }
        });
        executor.shutdown();
    }
}
