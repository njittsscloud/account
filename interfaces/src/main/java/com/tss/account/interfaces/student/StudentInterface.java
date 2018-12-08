package com.tss.account.interfaces.student;

import com.tss.account.interfaces.student.vo.UserBaseInfo;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.basic.site.user.annotation.StudentUser;

/**
 * StudentInterface
 * 
 * @author: MQG
 * @date: 2018/10/16
 */
public interface StudentInterface  {

    UserBaseInfo getUserBaseInfo(Long id);

    StudentUser getLoginInfo(String sessionId);

    LoginUserInfoVO getLoginInfoByUserAcc(String userAcc);
}
