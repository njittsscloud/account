package com.tss.account.services.student;

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

    @Value("${cookie.domain.studnet}")
    private String domain;
    
    @Autowired
    private StudentSessionDao studentSessionDao;
    

    protected String getSessionIdPrefix() {
        return "su";
    }

    /**
     * 保存session
     */
    public void saveSession(StudentSession studentSession) {
        studentSessionDao.insert(studentSession);
    }

    public void setLoginSessionCookie(HttpServletResponse response, String sessionId) {
        this.setCookie(response, CookieName.STUDENT.getCookieName(), sessionId);
    }

    // 浏览器不存储cookie
    private void setCookie(HttpServletResponse response, String name, String value) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setDomain(domain);
        response.addCookie(c);
    }

    public StudentSession findBySessionId(String sessionId) {
        return studentSessionDao.findBySessionId(sessionId);
    }
}
