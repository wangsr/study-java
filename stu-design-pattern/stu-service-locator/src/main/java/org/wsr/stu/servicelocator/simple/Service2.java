package org.wsr.stu.servicelocator.simple;

/**
 * Created by wangshengren on 16/10/26.
 */
public class Service2 implements Service {
    public String getName() {
        return "Service2";
    }

    public void execute() {
        System.out.println("Executing Service2");
    }
}
