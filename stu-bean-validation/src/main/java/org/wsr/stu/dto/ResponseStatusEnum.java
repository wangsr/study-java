package org.wsr.stu.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangsr
 * @date 2018/9/14
 * @description
 */
public enum ResponseStatusEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数错误"),
    INTERNAL_ERROR(2, "服务器内部错误");

    private int code;
    private String msg;

    ResponseStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构建内部静态映射
     */
    private static Map<Integer, ResponseStatusEnum> enumMap = new HashMap<>();

    static {
        Arrays.stream(ResponseStatusEnum.values()).forEach(status -> enumMap.put(status.code, status));
    }

    /**
     * 根据code转换为枚举
     *
     * @param code
     * @return
     */
    public static ResponseStatusEnum of(Integer code) {
        return enumMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
