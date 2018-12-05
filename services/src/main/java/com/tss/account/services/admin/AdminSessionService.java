package com.tss.account.services.admin;

import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.services.admin.dao.AdminSessionDao;
import com.tss.account.services.admin.po.AdminSession;
import com.tss.basic.site.user.item.CookieName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class AdminSessionService {
    private static final Logger LOG = LoggerFactory.getLogger(AdminSessionService.class);

    @Value("${cookie.domain.admin}")
    private String domain;
    
    @Autowired
    private AdminSessionDao adminSessionDao;
    

    public String getSessionIdPrefix() {
        return "au";
    }

    /**
     * 保存session
     */
    public void saveSession(AdminSession adminSession) {
        adminSessionDao.insertSelective(adminSession);
    }

    public void setLoginSessionCookie(HttpServletResponse response, String sessionId) {
        this.setCookie(response, CookieName.ADMIN.getCookieName(), sessionId);
    }

    // 浏览器不存储cookie
    private void setCookie(HttpServletResponse response, String name, String value) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setDomain(domain);
        response.addCookie(c);
    }

    public AdminSession findBySessionId(String sessionId) {
        return adminSessionDao.findBySessionId(sessionId);
    }

    public LoginUserInfoVO.CookieInfo getCookieInfo(String sessionId) {
        LoginUserInfoVO.CookieInfo cookieInfo = new LoginUserInfoVO.CookieInfo();
        cookieInfo.setCookieName(CookieName.ADMIN.getCookieName());
        cookieInfo.setCookieValue(sessionId);
        cookieInfo.setCookieDomain(domain);
        cookieInfo.setCookiePath("/");
        return cookieInfo;
    }
}

