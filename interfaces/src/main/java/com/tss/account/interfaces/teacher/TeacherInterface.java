package com.tss.account.interfaces.teacher;

import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.basic.site.user.annotation.TeacherUser;

/**
 * @author: MQG
 * @date: 2018/10/16
 */
public interface TeacherInterface {

    LoginUserInfoVO doLogin(UserIdentityVO userIdentity);
    
    TeacherUser getLoginInfo(String sessionId);
}
