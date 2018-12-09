package com.tss.account.web.teacher;

import com.tss.account.interfaces.teacher.TeacherInterface;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.account.services.login.AbstractUserLoginProcessor;
import com.tss.basic.site.argumentresolver.InternalJsonParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: MQG
 * @date: 2018/12/02
 */
@Api(value = "教师模块", tags = "TeacherController", description = "教师模块")
@RestController
@RequestMapping("/teacher")
public class TeacherAccountController {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherAccountController.class);
    
    @Autowired
    private TeacherInterface teacherInterface;
    @Autowired
    @Qualifier("teacherUserLoginProcessor")
    private AbstractUserLoginProcessor userLoginProcessor;

    @ApiOperation(value = "教师登录", notes = "教师登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginUserInfoVO login(HttpServletResponse response, @InternalJsonParam(validation = true) UserIdentityVO userIdentity) {
        return userLoginProcessor.doLogin(userIdentity);
    }

}