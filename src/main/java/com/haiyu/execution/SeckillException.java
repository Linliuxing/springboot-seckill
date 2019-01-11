package com.haiyu.execution;

/**
 * @Title: SeckillException
 * @Description: 秒杀相关的异常
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/11 14:54
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
