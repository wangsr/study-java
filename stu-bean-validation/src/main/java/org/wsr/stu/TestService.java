package org.wsr.stu;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author wangsr
 * @date 2018/9/14
 * @description
 */
@Service
@Validated
public class TestService {

    @NotBlank
    public String testStr(@NotNull String from) {
        String ret = null;
        if ("a".equals(from)) {
            return "a";
        }

        return ret;
    }

}
