package org.wsr.stu.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wsr.stu.autoconfig.pro.APro;
import org.wsr.stu.autoconfig.pro.BPro;

import java.util.List;

/**
 * Created by wangshengren on 2017/6/21.
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService hello;
    @Autowired
    private APro aPro;
    @Autowired
    private BPro bPro;

    @RequestMapping("/")
    public String index() {
        List<String> a = aPro.getConf();
        List<String> b = bPro.getConf();
        System.out.println(aPro);
        System.out.println(bPro);
        return hello.sayHello();
    }
}
