package com.likit.spring.exception;

/**
 * @author: LXY
 * @create: 2023-12-12 23:01
 * @Description:
 */
public class LikitException extends RuntimeException{

    public LikitException() {
    }

    public LikitException(String message) {
        super(message);
    }
}
