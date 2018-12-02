package com.tss.account.services.teacher;

import com.tss.account.services.student.dao.StudentSessionDao;
import com.tss.account.services.student.po.StudentSession;
import com.tss.account.services.teacher.dao.TeacherSessionDao;
import com.tss.account.services.teacher.po.TeacherSession;
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
public class TeacherSessionService {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherSessionService.class);

    @Value("${cookie.domain.teacher}")
    private String domain;
    
    @Autowired
    private TeacherSessionDao teacherSessionDao;
    

    protected String getSessionIdPrefix() {
        return "su";
    }

    /**
     * 保存session
     */
    public void saveSession(TeacherSession teacherSession) {
        teacherSessionDao.insert(teacherSession);
    }

    public void setLoginSessionCookie(HttpServletResponse response, String sessionId) {
        this.setCookie(response, CookieName.TEACHER.getCookieName(), sessionId);
    }

    // 浏览器不存储cookie
    private void setCookie(HttpServletResponse response, String name, String value) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setDomain(domain);
        response.addCookie(c);
    }

    public TeacherSession findBySessionId(String sessionId) {
        return teacherSessionDao.findBySessionId(sessionId);
    }
}
