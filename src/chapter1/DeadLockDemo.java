package chapter1;

/**
 * Created by xuran on 2018/12/6.
 * 死锁
 * 查看造成死锁的原因：jps  jsstack
 */
public class DeadLockDemo {

    private static String A = "A";
    private static String B = "B";

    private void deadLock() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("thread1 ===");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("thread2 ====");
                    }
                }

            }
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
}
