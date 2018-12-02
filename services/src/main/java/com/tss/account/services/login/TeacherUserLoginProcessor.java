package com.tss.account.services.login;

import com.tss.account.common.exception.GenSessionFailedException;
import com.tss.account.interfaces.enums.SessionStatusEnum;
import com.tss.account.services.teacher.TeacherSessionService;
import com.tss.account.services.teacher.dao.TeacherDao;
import com.tss.account.services.teacher.po.Teacher;
import com.tss.account.services.teacher.po.TeacherSession;
import com.tss.basic.common.util.ModelMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TeacherUserLoginProcessor extends AbstractUserLoginProcessor<Teacher> {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherUserLoginProcessor.class);

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherSessionService teacherSessionService;
    
    @Override
    public AbstractUserLoginProcessor getUserLoginProcessor() {
        return this;
    }

    @Override
    public AccountInfo<Teacher> findByAccount(String account) {
        Teacher teacher = teacherDao.findByAccount(account);
        if (teacher == null) {
            return null;
        }

        AccountInfo<Teacher> accountInfo = new AccountInfo<>();
        accountInfo.setPassword(teacher.getPassword());
        accountInfo.setSalt(teacher.getSalt());
        accountInfo.setOrigin(teacher);
        return accountInfo;
    }

    @Override
    public AccountSessionInfo saveSession(AccountInfo<Teacher> accountInfo) {
        TeacherSession teacherSession = new TeacherSession();
        teacherSession.setCreateTime(new Date());
        teacherSession.setDelFlag(0);
        teacherSession.setExpireTime(genExpireDate());
        String sessionId = genSessionId();
        if (sessionId == null) {
            LOG.error("bad session prefix {}", getSessionIdPrefix());
            throw new GenSessionFailedException("bad session id prefix.");
        }
        teacherSession.setSessionId(sessionId);
        teacherSession.setStatus(SessionStatusEnum.VALID.getId());
        teacherSession.setUserId(accountInfo.getOrigin().getId());
        teacherSession.setUserAcc(accountInfo.getOrigin().getJobNo());
        teacherSession.setUserName(accountInfo.getOrigin().getName());
        teacherSessionService.saveSession(teacherSession);

        return ModelMapperUtil.strictMap(teacherSession, AccountSessionInfo.class);
    }

    @Override
    public String getSessionIdPrefix() {
        return teacherSessionService.getSessionIdPrefix();
    }

}
