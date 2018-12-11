package util;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuran on 2018/12/11.
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
