package com.tss.account.web.student;

import com.tss.account.interfaces.student.StudentInterface;
import com.tss.account.interfaces.student.vo.UserBaseInfo;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.account.services.student.StudentSessionService;
import com.tss.basic.site.argumentresolver.InternalJsonParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * StudentController
 *
 * @author: MQG
 * @date: 2018/10/16
 */
@Api(value = "学生模块", tags = "StudentController", description = "学生模块")
@RestController
public class StudentController {
    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentInterface studentInterface;
    @Autowired
    private StudentSessionService studentSessionService;

    @ApiOperation(value = "学生登录", notes = "学生登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginUserInfoVO login(HttpServletResponse response, @InternalJsonParam(validation = true) UserIdentityVO userIdentity) {
        LoginUserInfoVO loginUserInfo = studentInterface.doLogin(userIdentity);
        studentSessionService.setLoginSessionCookie(response, loginUserInfo.getSessionId());
        return loginUserInfo;
    }

    @ApiOperation(value = "获取用户基本信息", notes = "获取用户基本信息")
    @RequestMapping(value = "/getUserBaseInfoById/{id}", method = RequestMethod.GET)
    public UserBaseInfo getUserBaseInfo(@PathVariable Long id) {
        return studentInterface.getUserBaseInfo(id);
    }
}
