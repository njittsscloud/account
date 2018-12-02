package com.tss.account.services.login;

import com.tss.account.common.exception.BadUserOrPasswordException;
import com.tss.account.common.util.UserUtil;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.basic.common.util.MD5Util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public abstract class AbstractUserLoginProcessor<T> {

    public abstract AbstractUserLoginProcessor getUserLoginProcessor();

    public abstract AccountInfo<T> findByAccount(String account);

    public abstract AccountSessionInfo saveSession(AccountInfo<T> accountInfo);

    public abstract String getSessionIdPrefix();


    public final LoginUserInfoVO doLogin(UserIdentityVO userIdentity) {
        AccountInfo accountInfo = getUserLoginProcessor().findByAccount(userIdentity.getUserName());
        if (accountInfo == null) {
            throw new BadUserOrPasswordException();
        }
        // 用户名或密码错误
        if (!UserUtil.hashPassword(userIdentity.getPassword(), accountInfo.getSalt()).equals(accountInfo.getPassword())) {
            throw new BadUserOrPasswordException();
        }

        // 生成保存session
        AccountSessionInfo sessionInfo = saveSession(accountInfo);
        // 登录用户信息
        return sessionUser2LoginUser(sessionInfo);
    }

    public final LoginUserInfoVO sessionUser2LoginUser(AccountSessionInfo sessionInfo) {
        LoginUserInfoVO loginUserInfo = new LoginUserInfoVO();
        loginUserInfo.setUserId(sessionInfo.getUserId());
        loginUserInfo.setUserAcc(sessionInfo.getUserAcc());
        loginUserInfo.setName(sessionInfo.getUserName());
        loginUserInfo.setCreateTime(sessionInfo.getCreateTime());
        loginUserInfo.setExpireDate(sessionInfo.getExpireTime());
        loginUserInfo.setSessionId(sessionInfo.getSessionId());
        loginUserInfo.setExtra(sessionInfo.getExtra());
        return loginUserInfo;
    }

    /**
     * 生成会话超时时间30天
     *
     * @return
     */
    protected static Date genExpireDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }

    protected String genSessionId() {
        UUID uuid = UUID.randomUUID();
        Random random = new Random();
        int r = random.nextInt(10000);

        String prefix = getSessionIdPrefix();
        if (prefix == null || prefix.length() > 6) {
            return null;
        }
        return prefix + "_" + MD5Util.hex(uuid.toString() + r).toLowerCase();
    }
}
