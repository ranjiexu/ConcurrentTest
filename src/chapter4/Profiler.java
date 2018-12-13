package chapter4;

import util.SleepUtils;

/**
 * Created by xuran on 2018/12/13.
 * ThreadLocal 演示
 */
public class Profiler {
    private static ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static void begin () {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static long end () {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) {
        Profiler.begin();
        SleepUtils.second(1);
        System.out.println("cost " + Profiler.end());
    }
}
