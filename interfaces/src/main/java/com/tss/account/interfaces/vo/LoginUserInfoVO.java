package com.tss.account.interfaces.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.Set;

/**
 * @author MQG
 * @date 2018/11/30
 */
public class LoginUserInfoVO<T> {
    private long userId;
    private String userAcc;
    private String name;
    private String password;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date expireDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String sessionId;
    private Set<String> permissions;
    private Set<String> roles;
    private T extra;
    private CookieInfo cookieInfo;

    public static class CookieInfo {
        private String cookiePath;
        private String cookieDomain;
        private String cookieName;
        private String cookieValue;

        public String getCookiePath() {
            return cookiePath;
        }

        public void setCookiePath(String cookiePath) {
            this.cookiePath = cookiePath;
        }

        public String getCookieDomain() {
            return cookieDomain;
        }

        public void setCookieDomain(String cookieDomain) {
            this.cookieDomain = cookieDomain;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }
    }

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public T getExtra() {
        return extra;
    }

    public void setExtra(T extra) {
        this.extra = extra;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CookieInfo getCookieInfo() {
        return cookieInfo;
    }

    public void setCookieInfo(CookieInfo cookieInfo) {
        this.cookieInfo = cookieInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
