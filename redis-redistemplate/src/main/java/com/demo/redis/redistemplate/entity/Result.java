package com.demo.redis.redistemplate.entity;

import com.demo.redis.redistemplate.ErrorCode;
import lombok.Data;

/**
 * 通用返回结果
 * @author Jia Shi
 * @since 2020/12/23
 */
@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result(){
    }

    public Result(Object data){
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMessage();
        this.data = data;
    }

    public Result(Integer code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
