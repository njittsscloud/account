package com.tss.account.common.exception;


import com.tss.basic.site.exception.BusinessException;

/**
 * @author MQG
 * @date 2018/11/30
 */
public class BadUserOrPasswordException extends BusinessException {
    public BadUserOrPasswordException() {
        super("用户名或密码错误!", 410000);
    }
}
