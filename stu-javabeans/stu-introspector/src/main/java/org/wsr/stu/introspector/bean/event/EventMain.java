package org.wsr.stu.introspector.bean.event;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Created by wangshengren on 16/10/12.
 */
public class EventMain {
    public static void main(String[] args) {
        EventBean eb = new EventBean();
        eb.setValue("set old value");
        eb.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("change====");
                System.out.println(evt);
            }
        });
        eb.addVetoableChangeListener(new VetoableChangeListener() {
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                System.out.println("veto====");
                System.out.println(evt);
            }
        });
        eb.setValue("set new value");
    }
}
