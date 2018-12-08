package com.tss.account.web.student;

import com.tss.account.interfaces.student.StudentInterface;
import com.tss.account.interfaces.student.vo.UserBaseInfo;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.account.services.login.AbstractUserLoginProcessor;
import com.tss.basic.site.argumentresolver.InternalJsonParam;
import com.tss.basic.site.user.annotation.StudentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: MQG
 * @date: 2018/10/16
 */
@Api(value = "学生模块", tags = "StudentController", description = "学生模块")
@RestController
@RequestMapping("/student")
public class StudentAccountController {
    private static final Logger LOG = LoggerFactory.getLogger(StudentAccountController.class);
    
    @Autowired
    private StudentInterface studentInterface;
    @Autowired
    @Qualifier("studentUserLoginProcessor")
    private AbstractUserLoginProcessor userLoginProcessor;

    @ApiOperation(value = "学生登录", notes = "学生登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginUserInfoVO login(HttpServletResponse response, @InternalJsonParam(validation = true) UserIdentityVO userIdentity) {
        return userLoginProcessor.doLogin(userIdentity);
    }

    @ApiOperation(value = "获取学生登录信息", notes = "获取学生登录信息")
    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    public StudentUser getLoginInfo(@CookieValue("studentsid") String sessionId) {
        return studentInterface.getLoginInfo(sessionId);
    }

    @ApiOperation(value = "获取用户基本信息", notes = "获取用户基本信息")
    @RequestMapping(value = "/getUserBaseInfoById/{id}", method = RequestMethod.GET)
    public UserBaseInfo getUserBaseInfo(@PathVariable Long id) {
        return studentInterface.getUserBaseInfo(id);
    }

}
