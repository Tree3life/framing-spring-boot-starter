package com.tree3.framing.handler.exception;


import com.tree3.framing.config.FramingProperties;
import com.tree3.framing.handler.bale.RespResult;
import com.tree3.framing.handler.bale.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 全局异常处理
 * @Author: Jinhui
 * @Date 2021/11/13 13:23
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private boolean showCase;

    @ExceptionHandler(value = BizException.class)
    public RespResult bizExceptionHandler(BizException e, HttpServletRequest request) {
        loggingCaseInfo(e, "自定义业务异常");
        return RespResult.exception(e.getResponseCode(), e.getMessage());
    }


    @ExceptionHandler(value = BindException.class)
    public RespResult bizArgumentHandler(BindException e, HttpServletRequest request) {

        loggingCaseInfo(e, "参数校验异常");
        return RespResult.exception(ResponseCode.PARAM_IS_INVALID, e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RespResult methodArgumentHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        loggingCaseInfo(e, "方法参数校验异常");
        return RespResult.exception(ResponseCode.PARAM_IS_INVALID, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RespResult exceptionHandler(Throwable e, HttpServletRequest request) {
        loggingCaseInfo(e, "未知异常");
        return RespResult.exception(ResponseCode.UNKNOWN_EXCEPTION, e.getMessage());
    }


    /**
     * 打印异常信息
     * 使用ExceptionUtil中的方法也能实现
     *
     * @param e
     * @param msg
     */
    private void loggingCaseInfo(Throwable e, String msg) {
        if (showCase) {
            logger.error("出错了");
            logger.error(msg + "===========>：" + e.getMessage());
            logger.error(ExceptionUtil.getMessage(e));
        }
    }

    public boolean isShowCase() {
        return showCase;
    }

    public void setShowCase(boolean showCase) {
        this.showCase = showCase;
    }

    public GlobalExceptionHandler(FramingProperties properties) {
        this.showCase = properties.isShowCase();
    }
}
