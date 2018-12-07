package org.wsr.stu.introspector.bean.event;

import java.beans.*;

/**
 * Created by wangshengren on 16/10/12.
 */
public class EventBean {
    private String value;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(propertyName, listener);
    }

    private final VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        this.vcs.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        this.vcs.removeVetoableChangeListener(listener);
    }

    public void addVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
        this.vcs.addVetoableChangeListener(propertyName, listener);
    }

    public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
        this.vcs.removeVetoableChangeListener(propertyName, listener);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        String oldValue = this.value;
        try {
            this.vcs.fireVetoableChange("value", oldValue, value);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        this.value = value;
        this.pcs.firePropertyChange("value", oldValue, value);
    }
}
