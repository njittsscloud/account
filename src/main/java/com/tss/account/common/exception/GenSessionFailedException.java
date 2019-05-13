package com.tss.account.common.exception;

import com.tss.basic.site.exception.BusinessException;

/**
 * @author MQG
 * @date 2018/11/30
 */
public class GenSessionFailedException extends BusinessException {
    public GenSessionFailedException(String msg) {
        super(msg);
    }
}
