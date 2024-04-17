package com.mychatroom.config;

import com.mychatroom.customException.UsernameExistException;
import com.mychatroom.customException.UsernameNotExistException;
import com.mychatroom.customException.UsernameOrPasswordWrongException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result exceptionHandler(Exception e){
        log.error("异常信息{}",e.getMessage());
        String errorMessage = e.getMessage();
        return Result.error(errorMessage);
    }

    @ExceptionHandler
    public Result usernameExistException(UsernameExistException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler
    public Result usernameOrPasswordWrongException(UsernameOrPasswordWrongException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler
    public Result usernameNotExistException(UsernameNotExistException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }
}
