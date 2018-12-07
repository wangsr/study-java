package org.wsr.stu.dto;

/**
 * @author wangsr
 * @date 2018/9/14
 * @description
 */
public class ResultDTO<T> {
    private int code;
    private String msg;
    private T data;

    private ResultDTO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /* ============ static tools ============= */

    /**
     * 快速生成“成功”结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 快速生成“成功”结果
     * （自定义成功消息）
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> success(String msg, T data) {
        return new ResultDTO<>(ResponseStatusEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 快速生成“参数错误”结果
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> paramError() {
        return new ResultDTO<>(ResponseStatusEnum.PARAM_ERROR.getCode(), ResponseStatusEnum.PARAM_ERROR.getMsg(), null);
    }

    /**
     * 快速生成“参数错误”结果
     * （自定义错误消息）
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> paramError(String msg) {
        return new ResultDTO<>(ResponseStatusEnum.PARAM_ERROR.getCode(), msg, null);
    }

    /**
     * 快速生成“服务器内部错误”结果
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> internalError() {
        return new ResultDTO<>(ResponseStatusEnum.INTERNAL_ERROR.getCode(), ResponseStatusEnum.INTERNAL_ERROR.getMsg(), null);
    }

    /**
     * 快速生成“服务器内部错误”结果
     * （自定义错误消息）
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> internalError(String msg) {
        return new ResultDTO<>(ResponseStatusEnum.INTERNAL_ERROR.getCode(), msg, null);
    }

    /**
     * 快速生成自定义错误结果
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> customError(int code, String msg) {
        return new ResultDTO<>(code, msg, null);
    }

    /* ============ getters & setters ============= */

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDTO{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
