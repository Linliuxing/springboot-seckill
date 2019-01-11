package com.haiyu.execution;

/**
 * @Title: RepeatKillException
 * @Description:  重复执行秒杀的异常（运行期异常）
 * @author: youqing
 * @version: 1.0
 * @date: 2019/1/11 14:55
 */
public class RepeatKillException extends SeckillException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
