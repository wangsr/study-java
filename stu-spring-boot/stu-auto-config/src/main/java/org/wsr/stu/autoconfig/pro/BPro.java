package org.wsr.stu.autoconfig.pro;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangshengren on 2017/6/23.
 */
@Component
@ConfigurationProperties(prefix = "field", locations = "classpath:b.yml")
public class BPro {
    private List<String> conf;

    public List<String> getConf() {
        return conf;
    }

    public void setConf(List<String> conf) {
        this.conf = conf;
    }
}
