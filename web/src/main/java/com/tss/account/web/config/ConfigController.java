package com.tss.account.web.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 config-client
 * 
 * @author: MQG
 * @date: 2018/10/18
 */
@Api(value = "测试config-client", tags = "ConfigController", description = "测试 config-client")
@RefreshScope
@RestController
@RequestMapping("/config")
public class ConfigController {
    
    @Value("${from}")
    private String from;
    
    @ApiOperation(value = "获取from属性", notes = "从config-server获取from属性")
    @RequestMapping(value = "/from", method = RequestMethod.GET)
    public String from() {
        return this.from;
    }
}
