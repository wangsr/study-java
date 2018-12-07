package org.wsr.stu.clazz;

/**
 * Created by wangshengren on 2017/4/25.
 */
public class StuInterrupt {

    public static void main(String[] args) throws InterruptedException {

        //中断一个不处于活动状态的线程不需要任何作用
        //正在执行的线程，需要用户自己判定中断行为
        //interrupt只是设置了一个中断状态而已，具体采取什么行为，取决于程序自身的决定
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    long time = System.currentTimeMillis();
                    while (System.currentTimeMillis() - time < 2000) {
                        if (this.isInterrupted()) {
                            //tread的成员方法isInterrupted()不会重置中断状态
                            System.out.println("被中断了，保持中断状态[this.isInterrupted() is ]" + this.isInterrupted());
                            //Thread.interrupted()静态方法，会重置中断状态
                            boolean flag = Thread.interrupted();
                            if (flag) {
                                System.out.println("被中断了，重置中断状态。");
                                System.out.println("old status is " + flag);
                                System.out.println("new status is " + Thread.interrupted());
                                //stop
                                return;
                            }
                        } else {
                            System.out.println("执行中。。。");
                        }
                    }
                    System.out.println("A1");
                } catch (Exception e) {
                    System.out.println("B1");
                }
            }
        };
        thread1.start();
        Thread.sleep(1);
        thread1.interrupt();

        //在线程sleep状态下进行中断
        Thread thread2 = new Thread() {
            public void run() {
                try {
                    //sleep中被interrupt，会抛出异常
                    Thread.sleep(2000);
                    System.out.println("A2");
                } catch (Exception e) {
                    System.out.println("B2");
                }
            }

        };

        thread2.start();
        thread2.interrupt();

        //在线程wait状态下进行中断,其中wait()没有在同步块中
        Thread thread3 = new Thread() {
            public void run() {
                try {
                    //object.wait
                    this.wait(2000);
                    System.out.println("A3");
                } catch (Exception e) {
                    System.out.println("B3");
                    //wait没有对象不持有对象锁，则抛出IllegalMonitorStateException。wait方法一般要在synchronized同步块、方法中
                    System.out.println(e);
                }
            }

        };

        thread3.start();
        thread3.interrupt();

        //在线程wait状态下进行中断,其中wait()在同步块中
        Thread thread4 = new Thread() {
            public void run() {
                try {
                    synchronized (this) {
                        this.wait(2000);
                        System.out.println("A4");
                    }
                } catch (Exception e) {
                    System.out.println("B4");
                    System.out.println(e);
                }
            }

        };

        thread4.start();
        thread4.interrupt();


        try {
            thread4.start();
            System.out.println("A5");
        } catch (Exception e) {
            System.out.println("B5");
            System.out.println(e.toString());
        }


    }

}
