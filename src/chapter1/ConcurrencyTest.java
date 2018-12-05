package chapter1;

/**
 * Created by xuran on 2018/12/5.
 * 测试串行和并行哪个更快，更改count值会发现运行时间不一样
 */
public class ConcurrencyTest {
    public static final long count = 1000000001;

    public static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 50;
        }
        int b = 0;
        for (long j = 0; j < count; j++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time);

    }

    public static void  concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 50;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long j = 0; j < count; j++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency:" + time);
    }

    public static void main(String[] args) throws InterruptedException {
        serial();
        concurrency();
        System.out.print("done");
    }
}
