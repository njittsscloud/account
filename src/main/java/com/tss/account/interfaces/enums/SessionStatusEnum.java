package com.tss.account.interfaces.enums;

/**
 * @author MQG
 * @date 2018/11/30
 */
public enum SessionStatusEnum {

    VALID(1, "可用"),
    INVALID(2, "过期");

    private int id;
    private String desc;

    SessionStatusEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
