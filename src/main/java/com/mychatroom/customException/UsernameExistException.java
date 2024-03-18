package com.mychatroom.customException;


/**
 * 用户名已存在
 */
public class UsernameExistException extends RuntimeException{
    public UsernameExistException() {
    }

    public UsernameExistException(String message) {
        super(message);
    }
}
