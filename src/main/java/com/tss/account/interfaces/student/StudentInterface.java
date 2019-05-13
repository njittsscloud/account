package com.tss.account.interfaces.student;

import com.tss.account.interfaces.student.vo.UserBaseInfo;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserAuthInfoVO;
import com.tss.basic.site.user.annotation.StudentUser;

/**
 * 学生信息
 *
 * @author: MQG
 * @date: 2018/10/16
 */
public interface StudentInterface {

    /**
     * 根据sessionId获取登录用户信息
     *
     * @param sessionId
     * @return
     */
    UserBaseInfo getUserBaseInfoBySessionId(String sessionId);

    StudentUser getLoginInfo(String userAcc);

    UserAuthInfoVO getAuthInfoByUserAcc(String userAcc);
}
