package org.wsr.stu.spi;

import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/**
 * Created by wangshengren on 2017/9/5.
 */
public class SLTest {
    public static void main(String[] args) {
        ServiceLoader<Search> sl = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = sl.iterator();
        while (iterator.hasNext()) {
            Search item = null;
            try {
                item = iterator.next();
            } catch (ServiceConfigurationError e) {
                //e.printStackTrace();
                System.out.println("============================");
                System.out.println("service not found process!!");
                System.out.println("============================");
                continue;
            }
            System.out.println(item);
            item.search("kw...");
        }
    }
}
