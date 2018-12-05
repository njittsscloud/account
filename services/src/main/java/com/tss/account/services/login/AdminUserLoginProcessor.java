package com.tss.account.services.login;

import com.tss.account.common.exception.GenSessionFailedException;
import com.tss.account.interfaces.enums.SessionStatusEnum;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.services.admin.AdminSessionService;
import com.tss.account.services.admin.dao.AdminDao;
import com.tss.account.services.admin.po.Admin;
import com.tss.account.services.admin.po.AdminSession;
import com.tss.basic.common.util.ModelMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AdminUserLoginProcessor extends AbstractUserLoginProcessor<Admin> {
    private static final Logger LOG = LoggerFactory.getLogger(AdminUserLoginProcessor.class);

    @Autowired
    private AdminDao adminDao;
    
    @Autowired
    private AdminSessionService adminSessionService;
    
    @Override
    public AccountInfo<Admin> findByAccount(String account) {
        Admin admin = adminDao.selectByAccount(account);
        if (admin == null) {
            return null;
        }

        AccountInfo<Admin> accountInfo = new AccountInfo<>();
        accountInfo.setPassword(admin.getPassword());
        accountInfo.setSalt(admin.getSalt());
        accountInfo.setOrigin(admin);
        return accountInfo;
    }

    @Override
    public AccountSessionInfo saveSession(AccountInfo<Admin> accountInfo) {
        AdminSession adminSession = new AdminSession();
        adminSession.setCreateTime(new Date());
        adminSession.setDelFlag(0);
        adminSession.setExpireTime(genExpireDate());
        String sessionId = genSessionId();
        if (sessionId == null) {
            LOG.error("bad session prefix {}", getSessionIdPrefix());
            throw new GenSessionFailedException("bad session id prefix.");
        }
        adminSession.setSessionId(sessionId);
        adminSession.setStatus(SessionStatusEnum.VALID.getId());
        adminSession.setUserId(accountInfo.getOrigin().getId());
        adminSession.setUserAcc(accountInfo.getOrigin().getAccount());
        adminSession.setUserName(accountInfo.getOrigin().getName());
        adminSessionService.saveSession(adminSession);

        return ModelMapperUtil.strictMap(adminSession, AccountSessionInfo.class);
    }

    @Override
    public String getSessionIdPrefix() {
        return adminSessionService.getSessionIdPrefix();
    }

    @Override
    public LoginUserInfoVO.CookieInfo getCookieInfo(String sessionId) {
        return adminSessionService.getCookieInfo(sessionId);
    }

}
