package com.tss.account.common.exception;

import com.tss.basic.site.exception.BusinessException;

/**
 * @author: MQG
 * @date: 2018/11/20
 */
public class DataCheckException extends BusinessException {

    public DataCheckException(String message) {
        super(message);
    }

    public DataCheckException(String message, Throwable cause) {
        super(message, cause);
    }

}
