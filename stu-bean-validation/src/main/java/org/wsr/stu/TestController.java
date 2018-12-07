package org.wsr.stu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * @author wangsr
 * @date 2018/9/14
 * @description
 */
@Slf4j
@RestController
@RequestMapping(path = "test")
@Validated
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(path = "add")
    public String add(
            @Validated(UserVO.Add.class) UserVO vo, @Min(value = 10L) @RequestParam(name = "age") int age) {
        log.info("vo is {}.", vo);
        testService.testStr(vo.getName());
        return "good boy";
    }

    @RequestMapping(path = "update")
    public String update(
            @Validated(UserVO.Update.class) UserVO vo) {
        log.info("vo is {}.", vo);
        testService.testStr(vo.getName());
        return "good boy";
    }
}
