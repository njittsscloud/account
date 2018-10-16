package com.tss.account.interfaces.student;

import com.tss.account.interfaces.student.vo.UserBaseInfo;

/**
 * StudentInterface
 * 
 * @author: MQG
 * @date: 2018/10/16
 */
public interface StudentInterface {
    UserBaseInfo getUserBaseInfo(Long id);
}
