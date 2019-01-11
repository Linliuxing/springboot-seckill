package com.haiyu.execution;

/**
 * @Title: SeckillCloseException
 * @Description:  秒杀关闭异常
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/11 14:56
 */
public class SeckillCloseException extends SeckillException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

}
