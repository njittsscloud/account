package com.tss.account.services.teacher;

import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.services.teacher.dao.TeacherSessionDao;
import com.tss.account.services.teacher.po.TeacherSession;
import com.tss.basic.site.user.item.CookieName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/30
 */
@Service
public class TeacherSessionService {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherSessionService.class);

    @Value("${cookie.domain.teacher}")
    private String domain;
    
    @Autowired
    private TeacherSessionDao teacherSessionDao;

    public String getSessionIdPrefix() {
        return "tu";
    }

    public void saveSession(TeacherSession teacherSession) {
        teacherSessionDao.insert(teacherSession);
    }

    public TeacherSession findBySessionId(String sessionId) {
        return teacherSessionDao.findBySessionId(sessionId);
    }

    public LoginUserInfoVO.CookieInfo getCookieInfo(String sessionId) {
        LoginUserInfoVO.CookieInfo cookieInfo = new LoginUserInfoVO.CookieInfo();
        cookieInfo.setCookieName(CookieName.TEACHER.getCookieName());
        cookieInfo.setCookieValue(sessionId);
        cookieInfo.setCookieDomain(domain);
        cookieInfo.setCookiePath("/");
        return cookieInfo;
    }
}
