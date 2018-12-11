package chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ranji
 * @date 2018/12/11
 * 测试线程的优先级，发现优先级的设定有作用
 * 说明此操作系统不会忽略线程对优先级的设定（Win10）
 */
public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notStop = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread: " + Thread.currentThread().getName() + ", run");
        List<Job> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int priority =  (i < 5) ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            list.add(job);
            Thread thread = new Thread(job, "Thread:" + i);
            thread.setPriority(priority);
            thread.start();
        }

        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notStop = false;

        for (Job job : list) {
            System.out.println("Thread priority:" + job.getPriority() + ", count:" + job.getCount());
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + ", run");
    }

    static class Job implements Runnable {
        private int priority;
        private long count;

        public Job(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        public long getCount() {
            return count;
        }

        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName() + ", run start");
            while (notStart) {
//                System.out.println("Thread: " + Thread.currentThread().getName() + ", notStart");
                Thread.yield();
            }
            while (notStop) {
//                System.out.println("Thread: " + Thread.currentThread().getName() + ", notStop");
                Thread.yield();
                count++;
            }
            System.out.println("Thread: " + Thread.currentThread().getName() + ", run end");
        }
    }
}
