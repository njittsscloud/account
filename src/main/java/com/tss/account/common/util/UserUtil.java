package com.tss.account.common.util;

import com.tss.basic.common.util.MD5Util;

import java.util.Random;

/**
 * @author MQG
 * @date 2018/11/30
 */
public class UserUtil {
    /**
     * 生成密码随机salt
     *
     * @return
     */
    public static String genSalt() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            buffer.append(random.nextInt(9));
        }
        return buffer.toString();
    }

    /**
     * 生成加密之后的hash值
     *
     * @param password
     * @param salt
     * @return
     */
    public static String hashPassword(String password, String salt) {
        String hex = MD5Util.hex(password + salt);
        System.out.println("hex:" + hex);
        return hex;
    }
}
