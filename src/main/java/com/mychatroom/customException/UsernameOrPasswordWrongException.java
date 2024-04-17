package com.mychatroom.customException;

/**
 * 密码或用户名错误
 */
public class UsernameOrPasswordWrongException extends RuntimeException{
    public UsernameOrPasswordWrongException() {
    }

    public UsernameOrPasswordWrongException(String message) {
        super(message);
    }
}
