package chapter6;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by xuran on 2018/12/19.
 * Fork/Join 框架演示代码
 */
public class CountTask extends RecursiveTask<Integer>{
    private static final int MIN_CAN_CUT = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果子任务足够小就计算子任务
        if ((end - start) <= MIN_CAN_CUT) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阀值，就裂分为两个子任务
            int midle = (start + end)/2;
            CountTask task1 = new CountTask(start, midle);
            CountTask task2 = new CountTask(midle + 1, end);
            // 执行子任务
            task1.fork();
            task2.fork();
            // 等待子任务执行结束，并得到结果
            int result1 = task1.join();
            int result2 = task2.join();
            // 合并结果
            sum = result1 + result2;
        }
        return sum;
    }

    public static void main(String[] args) {
        // 使用任务池装载任务
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(1, 100);
        Future<Integer> result = pool.submit(task);
        try {
            System.out.println("计算结果是：" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
