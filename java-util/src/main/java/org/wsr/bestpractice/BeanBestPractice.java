package org.wsr.bestpractice;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作bean的最佳实践
 * @author wangsr
 * @date 2018/8/3
 */
public class BeanBestPractice {
    /**
     * bean->map
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void describe() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Per bean = new Per();
        bean.setAge(19);
        bean.setName("wangsr");
        Map<String, String> map = BeanUtils.describe(bean);
    }

    /**
     *
     * map->bean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void populate() throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 18);
        map.put("name", "wangsr");
        Per bean = new Per();
        BeanUtils.populate(bean, map);
    }

    public void copy() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Per orig = new Per();
        Per dest = new Per();
        //避免用Apache Beanutils进行属性的copy。 说明：Apache BeanUtils性能较差，可以使用其他方案比如Spring BeanUtils, Cglib BeanCopier
        BeanUtils.copyProperties(dest, orig);
        BeanUtils.getProperty(dest, "name");
        BeanUtils.setProperty(dest, "name", "张三");
    }


    @Data
    public static class Per{
        private int age;
        private String name;
    }
}
