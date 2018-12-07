package org.wsr.stu.tool;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wsr.stu.dto.ResultDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author wangsr
 * @date 2018/9/14
 * @description 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultDTO<?> handle(HttpServletRequest req, Exception e) {
        logger.error("Uncaught exception. uri={} queryString={{}}", req.getRequestURI(), req.getQueryString(), e);
        return ResultDTO.internalError(e.getMessage());
    }

    /**
     * 处理参数校验失败的结果
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseBody
    public ResultDTO handleBindException(Exception ex) {
        String logMsg = "参数校验失败";
        String retMsg = logMsg;

        BindingResult result = null;
        if (ex instanceof BindException) {
            result = ((BindException) ex).getBindingResult();
        } else if (ex instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException) ex).getBindingResult();
        } else {
            ConstraintViolationException e = (ConstraintViolationException) ex;
            logMsg = e.getConstraintViolations().iterator().next().getMessage();
            retMsg = logMsg;
        }

        if (result != null) {
            if (result.hasFieldErrors()) {
                logMsg = result.getFieldError().getDefaultMessage();
                if (StringUtils.isNotBlank(logMsg)) {
                    retMsg = logMsg;
                }
            }
            if (result.hasGlobalErrors()) {
                logMsg = result.getGlobalError().getDefaultMessage();
            }
        }
        logger.error(logMsg);
        return ResultDTO.paramError(retMsg);
    }
}
