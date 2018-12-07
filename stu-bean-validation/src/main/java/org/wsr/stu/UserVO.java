package org.wsr.stu;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.wsr.stu.validation.CustomUrl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author wangsr
 * @date 2018/9/14
 * @description
 */
@Data
public class UserVO {
    private Integer id;
    @CustomUrl
    private String url;
    @NotBlank(message = "{name.not.blank}"/*国际化字符串*/,groups = {Add.class})
    private String name;
    @NotBlank(message = "desc 不允许为空空",groups = {Update.class})
    private String desc;

    /**
     * 分组验证:新增
     */
    public static class Add{}
    /**
     * 分组验证:更新
     */
    public static class Update{}



    //bean validation 2.0支持的注解
    /*
    1. @Null constraint
    2. @NotNull constraint
    3. @AssertTrue constraint
    4. @AssertFalse constraint
    5. @Min constraint
    6. @Max constraint
    7. @DecimalMin constraint
    8. @DecimalMax constraint
    9. @Negative constraint
    10. @NegativeOrZero constraint
    11. @Positive constraint
    12. @PositiveOrZero constraint
    13. @Size constraint
    14. @Digits constraint
    15. @Past constraint
    16. @PastOrPresent constraint
    17. @Future constraint
    18. @FutureOrPresent constraint
    19. @Pattern constraint
    20. @NotEmpty constraint
    21. @NotBlank constraint
    22. @Email constraint
     */
}
