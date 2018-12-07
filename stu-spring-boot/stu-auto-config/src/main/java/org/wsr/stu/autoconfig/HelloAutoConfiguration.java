package org.wsr.stu.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangshengren on 2017/6/21.
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)//开启属性注入,通过@autowired注入
@ConditionalOnClass(HelloService.class)//判断这个类是否在classpath中存在
//hello.enabled!=false或者不存在的时候，匹配
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true)
public class HelloAutoConfiguration {
    @Autowired
    private HelloProperties helloProperties;

    @Bean//使用java配置方式配置这个类
    @ConditionalOnMissingBean(HelloService.class)//容器中如果没有Hello这个类,那么自动配置这个Hello
    public HelloService hello() {
        HelloService hello = new HelloService();
        hello.setMsg(helloProperties.getMsg());
        return hello;
    }
}
