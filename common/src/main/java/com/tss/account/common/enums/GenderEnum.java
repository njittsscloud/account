/*
 * Copyright (C) 2017-2018 Qy All rights reserved
 * Author: Liu Tong
 * Date: 2018/12/5
 * Description:GenderEnum.java
 */
package com.tss.account.common.enums;

/**
 * 性别枚举
 *
 * @author Liu Tong
 */
public enum GenderEnum {
    MALE(1, "男"), FEMALE(2, "女");

    private Integer gender;

    private String genderName;

    GenderEnum(Integer gender, String genderName) {
        this.gender = gender;
        this.genderName = genderName;
    }

    public Integer getGender() {
        return gender;
    }

    public String getGenderName() {
        return genderName;
    }
}
