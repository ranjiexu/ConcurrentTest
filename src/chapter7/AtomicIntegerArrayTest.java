package chapter7;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by xuran on 2018/12/19.
 * 原子更新数组
 * 修改 AtomicIntegerArray 数组内部值，不会影响传入的数组的值
 */
public class AtomicIntegerArrayTest {
    static int[] value = {1, 2};
    static AtomicIntegerArray array = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        array.getAndSet(0, 3);
        System.out.println(array.get(0));
        System.out.println(value[0]);
    }
}
