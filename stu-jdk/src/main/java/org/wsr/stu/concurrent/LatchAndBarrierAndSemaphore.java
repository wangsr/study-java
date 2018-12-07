package org.wsr.stu.concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangshengren on 2017/6/30.
 */
public class LatchAndBarrierAndSemaphore {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        //CountDownLatch
        AtomicInteger cnt = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    //do something
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();//执行一次，减一个
                System.out.println("cnt is  " + cnt.getAndIncrement());
            }).start();
        }
        System.out.println("大家执行到什么状态，我不知道，调用await方法等待大家。。。");
        latch.await();//在大家执行完之前，我一直阻塞
        System.out.println("大家都执行完毕了, cnt is " + cnt.get());

        //CyclicBarrier
        AtomicInteger cnt02 = new AtomicInteger();
        CyclicBarrier barrier = new CyclicBarrier(10, () -> {
            System.out.println("大家都来了，才开始执行。。。");
        });
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    //do something
                    TimeUnit.MILLISECONDS.sleep(100);
                    barrier.await();//等待我的其他小伙伴，大家一起开始执行
                    System.out.println("do something ... " + cnt02.getAndIncrement());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        //Semaphore（许可的信号量）
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    //申请许可
                    System.out.println("申请许可");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                semaphore.release();
                //释放许可
                System.out.println("释放许可");
            }).start();
        }
    }
}
