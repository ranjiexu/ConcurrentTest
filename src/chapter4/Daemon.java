package chapter4;

import util.SleepUtils;

/**
 * Created by xuran on 2018/12/11.
 * 测试Daemon线程是否运行finally块中的内容，结论是不运行
 */
public class Daemon {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new DaemonRunner(), "DaemonThread");
        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run");
            }
        }
    }
}
