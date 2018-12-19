package chapter7;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xuran on 2018/12/19.
 * 原子更新基本类型操作类代码演示
 */
public class AtomicIntegerTest {
    static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {
        // 加并返回结果
        System.out.println(atomicInteger.addAndGet(1));
        // 自增，并返回旧值
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
        // 设置新值，并返回旧值
        System.out.println(atomicInteger.getAndSet(10));
        System.out.println(atomicInteger);
    }
}
