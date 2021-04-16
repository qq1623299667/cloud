package com.demo.redis.redistemplate;

/**
 * 错误码
 * @author Jia Shi
 * @since 2020/12/25
 */
public enum ErrorCode {
    SUCCESS(10000,"请求成功"),
    ;
    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
