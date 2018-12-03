package com.tss.account.services.student;

import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.services.student.dao.StudentSessionDao;
import com.tss.account.services.student.po.StudentSession;
import com.tss.basic.site.user.item.CookieName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: MQG
 * @date: 2018/11/30
 */
@Service
public class StudentSessionService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentSessionService.class);

    @Value("${cookie.domain.student}")
    private String domain;
    
    @Autowired
    private StudentSessionDao studentSessionDao;

    public String getSessionIdPrefix() {
        return "su";
    }

    public void saveSession(StudentSession studentSession) {
        studentSessionDao.insert(studentSession);
    }

    public StudentSession findBySessionId(String sessionId) {
        return studentSessionDao.findBySessionId(sessionId);
    }

    public LoginUserInfoVO.CookieInfo getCookieInfo(String sessionId) {
        LoginUserInfoVO.CookieInfo cookieInfo = new LoginUserInfoVO.CookieInfo();
        cookieInfo.setCookieName(CookieName.STUDENT.getCookieName());
        cookieInfo.setCookieValue(sessionId);
        cookieInfo.setCookieDomain(domain);
        cookieInfo.setCookiePath("/");
        return cookieInfo;
    }
}
