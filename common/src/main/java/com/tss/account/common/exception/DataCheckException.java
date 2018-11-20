package com.tss.account.common.exception;

/**
 * @author: MQG
 * @date: 2018/11/20
 */
public class DataCheckException extends RuntimeException {

    public DataCheckException(String message) {
        super(message);
    }

    public DataCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataCheckException(Throwable cause) {
        super(cause);
    }
}
