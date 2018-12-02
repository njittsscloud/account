package com.tss.account.services.teacher.po;

import java.util.Date;

public class TeacherSession {
    /**
     *id
     */
    private Long id;

    /**
     *用户id
     */
    private Long userId;

    /**
     *登录用户名
     */
    private String userAcc;

    /**
     *登录用户姓名
     */
    private String userName;

    /**
     *session id
     */
    private String sessionId;

    /**
     *状态 1 可用 2 过期
     */
    private Integer status;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *过期时间
     */
    private Date expireTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *删除标志 0 正常 1删除
     */
    private Integer delFlag;

    /**
     *扩展信息
     */
    private String extra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}