/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: Liu Tong
 * Date: 2018/12/5
 * Description:AdminInterface.java
 */
package com.tss.account.interfaces.admin;

import com.tss.account.interfaces.admin.vo.AdminBaseInfoVo;
import com.tss.basic.site.user.annotation.AdminUser;

/**
 * AdminInterface
 * 
 * @author Liu Tong
 */
public interface AdminInterface {
    
    AdminBaseInfoVo getUserBaseInfo(Long id);

    AdminUser getLoginInfo(String sessionId);
}
