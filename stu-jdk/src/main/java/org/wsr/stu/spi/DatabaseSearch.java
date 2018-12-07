package org.wsr.stu.spi;

/**
 * Created by wangshengren on 2017/9/5.
 */
public class DatabaseSearch implements Search {
    @Override
    public void search(String kw) {
        System.out.println("database search --> " + kw);
    }
}
