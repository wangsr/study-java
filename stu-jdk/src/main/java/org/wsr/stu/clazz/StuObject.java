package org.wsr.stu.clazz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Object对象学习
 * Created by wangshengren on 2017/4/20.
 */
public class StuObject {
    public static void main(String[] args) throws Exception {
        //1、clone
        CloneObj c = new CloneObj();
        CloneObj copy = c.clone();
        System.out.println(c != copy);
        System.out.println(c.getList() == copy.getList());

        //2、equals与hashCode需要同事覆写

        //3、finalize，当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法。

        //4、getClass()，返回此 Object 的运行时类。

        //5、notify,notifyAll,wait
        WaitNotifyObj obj = new WaitNotifyObj();
        new Thread(() -> obj.waitFun()).start();
        new Thread(() -> obj.notifyFun()).start();

    }

    /**
     * Obj.wait()，与Obj.notify()必须要与synchronized(Obj)一起使用，
     * 也就是wait,与notify是针对已经获取了Obj锁进行操作，
     * 从语法角度来说就是Obj.wait(),Obj.notify必须在synchronized(Obj)
     * {…}语句块内。从功能上来说wait就是说线程在获取对象锁后，主动释放对象锁，
     * 同时本线程休眠。直到有其它线程调用对象的notify()唤醒该线程，才能继续获取对象锁，
     * 并继续执行。相应的notify()就是对对象锁的唤醒操作。但有一点需要注意的是notify()
     * 调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，
     * 自动释放锁后，JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
     * 这样就提供了在线程间同步、唤醒的操作。
     * Thread.sleep()与Object.wait()二者都可以暂停当前线程，释放CPU控制权，
     * 主要的区别在于Object.wait()在释放CPU同时，释放了对象锁的控制。
     */
    public static class WaitNotifyObj {

        private Object synObj = new Object();

        public void waitFun() {
            synchronized (synObj) {
                System.out.println("T1获取synObj的对象监视器，开始执行同步块");
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("sync block running, sleep不会释放锁 ...");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    System.out.println("T1在 wait()时挂起了，wait会释放锁");
                    synObj.wait();
                    System.out.println("T1被T2唤醒后并重新获得synObj的对象监视器，继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1获取synObj的对象监视器，结束同步块");
            }
        }

        public void notifyFun() {
            System.out.println("T2启动，但是因为T1占用了synObj的对象监视器，则等待T1执行synObj.wait来释放它");
            synchronized (synObj) {
                try {
                    System.out.println("在T1执行synObj.wait后，T2获取synObj的对象监视器，进入同步块");
                    synObj.notify();
                    System.out.println("T2执行synObj.notify()，T1被唤醒，但T2还在同步块中，没有释放synObj的对象监视器，T1等待synObj的对象监视器");
                    for (int i = 0; i < 5; i++) {
                        System.out.println("sync block running, sleep不会释放锁 ...");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    System.out.println("T2结束同步块，释放synObj的对象监视器，T1获取到synObj的对象监视器，并执行wait后面的操作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * java中Object的clone是浅拷贝
     * 1、创建一个新对象，新对象的属性使用被拷贝对象的值初始化;
     * 2、被拷贝的方法必须实现cloneable接口
     */
    public static class CloneObj implements Cloneable {
        private int age = 10;
        private String name = "wangsr";
        private List<String> list = new ArrayList<>();


        public CloneObj clone() throws CloneNotSupportedException {
            return (CloneObj) super.clone();
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }


}
