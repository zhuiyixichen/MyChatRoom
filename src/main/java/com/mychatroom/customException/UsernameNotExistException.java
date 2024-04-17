package com.mychatroom.customException;

/**
 * 用户名不存在，请注册
 */
public class UsernameNotExistException extends RuntimeException{
    public UsernameNotExistException() {
    }

    public UsernameNotExistException(String message) {
        super(message);
    }
}
