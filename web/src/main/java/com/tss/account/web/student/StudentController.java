package com.tss.account.web.student;

import com.tss.account.interfaces.student.StudentInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    
    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login() {
        
    }
}
