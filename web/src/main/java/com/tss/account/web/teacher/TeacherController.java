package com.tss.account.web.teacher;

import com.tss.account.interfaces.teacher.TeacherInterface;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.account.services.student.StudentSessionService;
import com.tss.account.services.teacher.TeacherSessionService;
import com.tss.basic.site.argumentresolver.InternalJsonParam;
import com.tss.basic.site.user.annotation.TeacherUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
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
public class TeacherController {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    
    @Autowired
    private TeacherInterface teacherInterface;
    @Autowired
    private TeacherSessionService teacherSessionService;

    @ApiOperation(value = "教师登录", notes = "教师登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginUserInfoVO login(HttpServletResponse response, @InternalJsonParam(validation = true) UserIdentityVO userIdentity) {
        LoginUserInfoVO loginUserInfo = teacherInterface.doLogin(userIdentity);
        teacherSessionService.setLoginSessionCookie(response, loginUserInfo.getSessionId());
        return loginUserInfo;
    }

    @ApiOperation(value = "获取教师登录信息", notes = "获取教师登录信息")
    @RequestMapping(value = "/getLoginInfo", method = RequestMethod.GET)
    public TeacherUser getLoginInfo(@CookieValue("teachersid") String sessionId) {
        return teacherInterface.getLoginInfo(sessionId);
    }

}
