package com.tss.account.web.student;

import com.tss.account.interfaces.student.StudentInterface;
import com.tss.account.interfaces.vo.UserAuthInfoVO;
import com.tss.basic.site.user.annotation.StudentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MQG
 * @date: 2018/12/05
 */
@Api(value = "学生信息模块", tags = "StudentInfoController", description = "学生信息模块")
@RestController
@RequestMapping("/student/info")
public class StudentInfoController {

    @Autowired
    private StudentInterface studentInterface;

    @ApiOperation(value = "根据账号获取用户认证信息", notes = "（认证服务器调用）根据账号获取用户认证信息")
    @RequestMapping(value = "/getAuthInfoByUserAcc/{userAcc}", method = RequestMethod.GET)
    public UserAuthInfoVO getAuthInfoByUserAcc(@PathVariable("userAcc") String userAcc) {
        return studentInterface.getAuthInfoByUserAcc(userAcc);
    }

    @ApiOperation(value = "获取获取学生登录信息", notes = "（登录时根据账号获取）学生登录")
    @RequestMapping(value = "/getLoginInfo/{userAcc}", method = RequestMethod.GET)
    public StudentUser getLoginInfo(@PathVariable("userAcc") String userAcc) {
        return studentInterface.getLoginInfo(userAcc);
    }
}
