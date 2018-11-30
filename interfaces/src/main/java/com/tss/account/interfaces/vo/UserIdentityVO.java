package com.tss.account.interfaces.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author MQG
 * @date 2018/11/30
 */
@ApiModel("用户登录信息")
public class UserIdentityVO {
    
    @ApiModelProperty(value = "学号 工号 账号", example = "20181130001")
    @NotEmpty(message = "账号不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", example = "123456")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "图片验证码")
    private String code;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
