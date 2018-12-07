/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: Liu Tong
 * Date: 2018/12/5
 * Description:AdminController.java
 */
package com.tss.account.admin;

import com.tss.account.interfaces.admin.AdminInterface;
import com.tss.account.interfaces.admin.vo.AdminBaseInfoVo;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.account.services.admin.AdminSessionService;
import com.tss.account.services.login.AbstractUserLoginProcessor;
import com.tss.basic.site.argumentresolver.JsonParam;
import com.tss.basic.site.user.annotation.AdminUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Liu Tong
 */
@Api(value = "管理员模块", tags = "AdminController", description = "管理员模块")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminInterface adminInterface;
    @Autowired
    private AdminSessionService adminSessionService;

    @Autowired
    @Qualifier("adminUserLoginProcessor")
    private AbstractUserLoginProcessor userLoginProcessor;

    @ApiOperation(value = "管理员登录", notes = "管理员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginUserInfoVO login(HttpServletResponse response, @JsonParam(validation = true) UserIdentityVO userIdentity) {
        return userLoginProcessor.doLogin(userIdentity);
        
    }

    @ApiOperation(value = "获取管理员登录信息", notes = "管理员登录")
    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    public AdminUser getLoginInfo(@CookieValue("adminsid") String sessionId) {
        return adminInterface.getLoginInfo(sessionId);
    }

    @ApiOperation(value = "获取用户基本信息", notes = "获取用户基本信息")
    @RequestMapping(value = "/getUserBaseInfoById/{id}", method = RequestMethod.GET)
    public AdminBaseInfoVo getUserBaseInfo(@PathVariable Long id) {
        return adminInterface.getUserBaseInfo(id);
    }
}
