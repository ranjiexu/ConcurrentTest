package chapter4;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by xuran on 2018/12/13.
 * 管道输入/输出测试
 * ⚠️一定要connect，不然会报错
 */
public class Piped {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
//        in.connect(out);
        Thread pipedThread = new Thread(new Runner(in), "pipedThread");
        pipedThread.start();
        int receiver = 0;
        try {
            while ((receiver = System.in.read()) != -1) {
                out.write((char)receiver);
            }
        } finally {
            out.close();
        }
    }

    static class Runner implements Runnable {

        PipedReader in;

        public Runner(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receiver = 0;
            try {
                while ((receiver = in.read()) != -1) {
                    System.out.print((char) receiver);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
