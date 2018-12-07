package org.wsr.stu.introspector.bean;

import sun.reflect.misc.ReflectUtil;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;

/**
 * javabean的示例
 * <pre>
 *     1、bean的基本组成:属性,方法,事件
 *     2、
 * </pre>
 * Created by wangshengren on 16/10/8.
 */
public class JavaBeanDemo {
    //简单属性
    private String simplePro;
    //索引属性
    private String [] indexedPro;
    //绑定属性
    private String boundedPro;
    //约束属性
    private String constrainedPro;

    //实例化一个propertyChange对象
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    //实例化一个vetoChange对象
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);



    // accessor method

    public String getSimplePro() {
        return simplePro;
    }

    public void setSimplePro(String simplePro) {
        this.simplePro = simplePro;
    }

    // note that indexed property
    public String[] getIndexedPro() {
        return indexedPro;
    }

    public String getIndexedPro(int index) {
        return indexedPro[index];
    }

    public void setIndexedPro(String[] indexedPro) {
        this.indexedPro = indexedPro;
    }

    public void setIndexedPro(int index, String indexedPro) {
        this.indexedPro[index] = indexedPro;
    }

    public String getBoundedPro() {
        return boundedPro;
    }

    public void setBoundedPro(String boundedPro) {
        String oldValue = this.boundedPro;
        this.boundedPro = boundedPro;
        propertyChangeSupport.firePropertyChange("boundedPro", oldValue, boundedPro);
    }

    public String getConstrainedPro() {
        return constrainedPro;
    }

    public void setConstrainedPro(String constrainedPro) {
        String oldValue = constrainedPro;
        try {
            vetoableChangeSupport.fireVetoableChange("constrainedPro", oldValue, constrainedPro);
        } catch (PropertyVetoException e) {
            //抛出异常,表示不满足、不接受
            e.printStackTrace();
        }
        this.constrainedPro = constrainedPro;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        //all properties
        this.propertyChangeSupport.addPropertyChangeListener(listener);
        // the specific property
        this.propertyChangeSupport.addPropertyChangeListener("boundedPro", listener);
    }
}
