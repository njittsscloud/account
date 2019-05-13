package com.tss.account.services.teacher.po;

import java.util.Date;

public class Teacher {
    /**
     *id
     */
    private Long id;

    /**
     *工号
     */
    private String jobNo;

    /**
     *密码
     */
    private String password;

    /**
     *盐值
     */
    private String salt;

    /**
     *姓名
     */
    private String name;

    /**
     *头像
     */
    private String headImg;

    /**
     *性别 0保密 1男 2女
     */
    private Integer gender;

    /**
     *手机号码
     */
    private String phone;

    /**
     *邮箱
     */
    private String email;

    /**
     *描述
     */
    private String desc;

    /**
     *学院id
     */
    private Long academicId;

    /**
     *学院名称
     */
    private String academicName;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *修改时间
     */
    private Date updateTime;

    /**
     *删除标志 0未删除 1已删除
     */
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getAcademicId() {
        return academicId;
    }

    public void setAcademicId(Long academicId) {
        this.academicId = academicId;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}