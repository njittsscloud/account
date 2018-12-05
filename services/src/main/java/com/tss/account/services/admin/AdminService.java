/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: Liu Tong
 * Date: 2018/12/5
 * Description:AdminService.java
 */
package com.tss.account.services.admin;

import com.tss.account.common.exception.DataCheckException;
import com.tss.account.interfaces.admin.AdminInterface;
import com.tss.account.interfaces.admin.vo.AdminBaseInfoVo;
import com.tss.account.services.admin.dao.AdminDao;
import com.tss.account.services.admin.po.Admin;
import com.tss.account.services.admin.po.AdminSession;
import com.tss.basic.common.util.MD5Util;
import com.tss.basic.site.user.annotation.AdminUser;
import com.tss.basic.site.util.TSSAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author Liu Tong
 */
@Service
public class AdminService implements AdminInterface {
    private Logger LOG = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    AdminDao adminDao;

    @Autowired
    AdminSessionService adminSessionService;

    @Override
    public AdminBaseInfoVo getUserBaseInfo(Long id) {
        Admin admin = adminDao.selectByPrimaryKey(id);
        if (admin == null) {
            throw new DataCheckException("无效的id");
        }
        AdminBaseInfoVo adminBaseInfoVo = new AdminBaseInfoVo();
        adminBaseInfoVo.setId(admin.getId());
        adminBaseInfoVo.setAdminAcc(admin.getAccount());
        adminBaseInfoVo.setName(admin.getName());
        adminBaseInfoVo.setGender(admin.getGender());
        adminBaseInfoVo.setEmail(admin.getEmail());
        adminBaseInfoVo.setHeadImag(admin.getHeadImg());
        adminBaseInfoVo.setPhone(admin.getPhone());

        return adminBaseInfoVo;
    }

    @Override
    public AdminUser getLoginInfo(String sessionId) {
        AdminSession adminSession = adminSessionService.findBySessionId(sessionId);
        TSSAssert.isNotNull(adminSession, "session 无效");
        Admin admin = adminDao.selectByPrimaryKey(adminSession.getUserId());
        TSSAssert.isNotNull(admin, "session 无效");

        AdminUser adminUser = new AdminUser();
        adminUser.setAdminId(admin.getId());
        adminUser.setAdminAcc(admin.getAccount());
        adminUser.setAdminName(admin.getName());
        adminUser.setAcademicId(admin.getAcademicId());
        adminUser.setAcademicName(admin.getAcademicName());

        return adminUser;
    }

    /**
     * 生成回话超时时间固定30天
     *
     * @return
     */
    private static Date genExpireDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }

    protected final String genSessionId() {
        UUID uuid = UUID.randomUUID();
        Random random = new Random();
        int r = random.nextInt(10000);

        String prefix = adminSessionService.getSessionIdPrefix();
        if (prefix == null || prefix.length() > 6) {
            return null;
        }
        return prefix + "_" + MD5Util.hex(uuid.toString() + r).toLowerCase();
    }
}
