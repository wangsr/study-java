package org.wsr.stu.servicelocator.simple;

/**
 * Created by wangshengren on 16/10/26.
 */
public class Service1 implements Service {
    public String getName() {
        return "Service1";
    }

    public void execute() {
        System.out.println("Executing Service1");
    }
}
