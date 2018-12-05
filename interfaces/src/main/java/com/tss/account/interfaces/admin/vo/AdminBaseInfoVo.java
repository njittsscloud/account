/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: Liu Tong
 * Date: 2018/12/5
 * Description:AdminBaseInfoVo.java
 */
package com.tss.account.interfaces.admin.vo;

/**
 * 管理员基本信息
 * 
 * @author Liu Tong
 */
public class AdminBaseInfoVo {

    private Long id;
    private String adminAcc;
    private String headImag;
    private String name;
    private String phone;
    private String email;
    private Integer gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminAcc() {
        return adminAcc;
    }

    public void setAdminAcc(String adminAcc) {
        this.adminAcc = adminAcc;
    }

    public String getHeadImag() {
        return headImag;
    }

    public void setHeadImag(String headImag) {
        this.headImag = headImag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
