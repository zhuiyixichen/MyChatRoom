package com.mychatroom.config;


import lombok.Data;

import java.io.Serializable;

@Data
public class  Result <T> implements Serializable {
    private Integer code;//1.正常；2.错误
    private String message;
    private T data;


    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T t){
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = t;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.code = 0;
        result.message = msg;
        return result;
    }
}
