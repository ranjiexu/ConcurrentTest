package chapter8;

import java.util.concurrent.*;

/**
 * Created by xuran on 2018/12/21.
 * 同步屏障的运用
 * 多个线程计算结果，并将结果汇总
 *
 */
public class BankWaterService implements Runnable {
    // 设置4个屏障
    private CyclicBarrier barrier = new CyclicBarrier(4, this);
    // 设置固定为4的线程池
    private Executor executor = Executors.newFixedThreadPool(4);
    // 创建结果容器，需要线程安全的hashMap
    private ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    private void count () {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 省去计算结果，直接把结果放入容器中
                    concurrentHashMap.put(Thread.currentThread().getName(), 1);
                    // 设置屏障，等待所有的结果计算完成
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        // 合并所有线程计算的结果
        for (ConcurrentHashMap.Entry<String, Integer> entry : concurrentHashMap.entrySet()) {
            result += entry.getValue();
        }
        concurrentHashMap.put("result", result);
        System.out.println("最终的计算结果：" + result);
    }

    public static void main(String[] args) {
        BankWaterService service = new BankWaterService();
        service.count();
    }
}
