package com.tss.account.web.teacher;

import com.tss.account.interfaces.teacher.TeacherInterface;
import com.tss.account.interfaces.vo.UserAuthInfoVO;
import com.tss.basic.site.user.annotation.TeacherUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: MQG
 * @date: 2018/12/09
 */
@Api(value = "教师信息模块", tags = "TeacherInfoController", description = "教师信息模块")
@RestController
@RequestMapping("/teacher/info")
public class TeacherInfoController {

    @Autowired
    private TeacherInterface teacherInterface;

    @ApiOperation(value = "根据账号获取用户认证信息", notes = "（认证服务器调用）根据账号获取用户认证信息")
    @RequestMapping(value = "/getAuthInfoByUserAcc/{userAcc}", method = RequestMethod.GET)
    public UserAuthInfoVO getAuthInfoByUserAcc(@PathVariable("userAcc") String userAcc) {
        return teacherInterface.getAuthInfoByUserAcc(userAcc);
    }

    @ApiOperation(value = "获取获取教师登录信息", notes = "（登录时根据账号获取）教师登录")
    @RequestMapping(value = "/getLoginInfo/{userAcc}", method = RequestMethod.GET)
    public TeacherUser getLoginInfo(@PathVariable("userAcc") String userAcc) {
        return teacherInterface.getLoginInfo(userAcc);
    }
}
