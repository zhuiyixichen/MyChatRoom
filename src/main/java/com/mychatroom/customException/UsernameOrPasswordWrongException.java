package com.mychatroom.customException;

/**
 * 该用户不存在
 */
public class UsernameOrPasswordWrongException extends RuntimeException{
    public UsernameOrPasswordWrongException() {
    }

    public UsernameOrPasswordWrongException(String message) {
        super(message);
    }
}
