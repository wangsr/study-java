package org.wsr.stu.concurrent;

import java.util.concurrent.*;

/**
 * Created by wangshengren on 2017/7/2.
 */


/**
 * Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。
 * <p>
 * 当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，
 * 直到线程B也调用了exchange()方法，然后以线程安全的方式交换数据，
 * 之后线程A和B继续运行
 */
public class ExchangerTest {
    private static volatile boolean isDone = false;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe = Executors.newCachedThreadPool();
        Exchanger<Integer> exchanger = new Exchanger<>();
        exe.submit(new ExchangerProducer(exchanger));
        exe.submit(new ExchangerConsumer(exchanger));
        exe.shutdown();
        exe.awaitTermination(10, TimeUnit.SECONDS);
    }

    static class ExchangerProducer implements Runnable {
        private Integer data;
        private Exchanger<Integer> exchanger;

        public ExchangerProducer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    data = i;
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("producer exchange before is " + data);
                    data = exchanger.exchange(data, 2, TimeUnit.SECONDS);
                    System.out.println("producer exchange after is " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            isDone = true;
        }
    }


    static class ExchangerConsumer implements Runnable {
        private Integer data;
        private Exchanger<Integer> exchanger;

        public ExchangerConsumer(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (!isDone) {
                data = 1000;
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("consumer exchange before is " + data);
                    data = exchanger.exchange(data, 2, TimeUnit.SECONDS);
                    System.out.println("consumer exchange after is " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
