package com.tree3.framing.handler.bale;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description 统一封装的请求结果
 * @Author: Jinhui
 * @Date 2021/11/9 9:36
 */
public class RespResult implements Serializable {
    private Integer code;
    private String message;
    private Object data;


    public RespResult() {
    }

    public RespResult(ResponseCode responseCode) {
        this.code = responseCode.code();
        this.message = responseCode.message();
    }

    public RespResult(ResponseCode responseCode, Object data) {
        this.code = responseCode.code();
        this.message = responseCode.message();
        this.data = data;
    }


    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static RespResult success(Object data) {
        return new RespResult(ResponseCode.SUCCESS, data);
    }

    /**
     * 失败结果
     *
     * @param code
     * @param data
     * @return
     */
    public static RespResult failure(ResponseCode code, Object data) {
        return new RespResult(code, data);
    }


    /**
     * 异常处理结果
     *
     * @param responseCode
     * @param errorMsg
     * @return
     */
    public static ErrorResult exception(ResponseCode responseCode, String errorMsg) {
        return new ErrorResult(responseCode, errorMsg);
    }

    /**
     * 异常处理结果
     *
     * @param errorMsg
     * @return
     */
    public static ErrorResult exception(String errorMsg) {
        return new ErrorResult(errorMsg);
    }

    /**
     * 对异常结果进行封装
     */
    static class ErrorResult extends RespResult {
        private String errmsg;

        public ErrorResult() {
            super();
        }

        public ErrorResult(String errorMsg) {
            super();
            this.errmsg = errorMsg;
        }

        public ErrorResult(ResponseCode responseCode, String errmsg) {
            super(responseCode);
            this.errmsg = errmsg;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        @Override
        public String toString() {
            return "ErrorResult{" +
                    "errmsg='" + errmsg + '\'' +
                    "} " + super.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RespResult that = (RespResult) o;
        return Objects.equals(code, that.code) && Objects.equals(message, that.message) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RespResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
