package com.tss.account.interfaces.teacher;

import com.tss.basic.site.user.annotation.TeacherUser;

/**
 * @author: MQG
 * @date: 2018/10/16
 */
public interface TeacherInterface {

    TeacherUser getLoginInfo(String sessionId);
}
