package com.tss.account.web.student;

import com.tss.account.interfaces.student.StudentInterface;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
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

    @ApiOperation(value = "根据账号获取登录信息", notes = "根据账号获取登录信息（学号）")
    @RequestMapping(value = "/getLoginInfoByUserAcc/{userAcc}", method = RequestMethod.GET)
    public LoginUserInfoVO getLoginInfoByUserAcc(@PathVariable("userAcc") String userAcc) {
        return studentInterface.getLoginInfoByUserAcc(userAcc);
    }
}
