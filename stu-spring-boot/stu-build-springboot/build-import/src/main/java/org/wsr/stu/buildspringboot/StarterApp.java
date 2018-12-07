package org.wsr.stu.buildspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangshengren on 2017/6/25.
 */
@Controller
@SpringBootApplication
public class StarterApp {
    public static void main(String[] args) {
        SpringApplication.run(StarterApp.class, args);
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
