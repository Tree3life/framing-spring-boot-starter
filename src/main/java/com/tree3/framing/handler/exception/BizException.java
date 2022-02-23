package com.tree3.framing.handler.exception;


import com.tree3.framing.handler.bale.ResponseCode;

/**
 * @Description 自定义业务相关的异常
 * @Author: Jinhui
 * @Date 2021/11/13 13:19
 */
public class BizException extends RuntimeException {

    private ResponseCode responseCode;


    public BizException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public BizException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
