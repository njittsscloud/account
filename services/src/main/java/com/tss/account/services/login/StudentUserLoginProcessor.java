package com.tss.account.services.login;

import com.tss.account.common.exception.GenSessionFailedException;
import com.tss.account.interfaces.enums.SessionStatusEnum;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.services.student.StudentSessionService;
import com.tss.account.services.student.dao.StudentDao;
import com.tss.account.services.student.po.Student;
import com.tss.account.services.student.po.StudentSession;
import com.tss.basic.common.util.ModelMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StudentUserLoginProcessor extends AbstractUserLoginProcessor<Student> {
    private static final Logger LOG = LoggerFactory.getLogger(StudentUserLoginProcessor.class);

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentSessionService studentSessionService;
    
    @Override
    public AccountInfo<Student> findByAccount(String account) {
        Student student = studentDao.findByAccount(account);
        if (student == null) {
            return null;
        }

        AccountInfo<Student> accountInfo = new AccountInfo<>();
        accountInfo.setPassword(student.getPassword());
        accountInfo.setSalt(student.getSalt());
        accountInfo.setOrigin(student);
        return accountInfo;
    }

    @Override
    public AccountSessionInfo saveSession(AccountInfo<Student> accountInfo) {
        StudentSession studentSession = new StudentSession();
        studentSession.setCreateTime(new Date());
        studentSession.setDelFlag(0);
        studentSession.setExpireTime(genExpireDate());
        String sessionId = genSessionId();
        if (sessionId == null) {
            LOG.error("bad session prefix {}", getSessionIdPrefix());
            throw new GenSessionFailedException("bad session id prefix.");
        }
        studentSession.setSessionId(sessionId);
        studentSession.setStatus(SessionStatusEnum.VALID.getId());
        studentSession.setUserId(accountInfo.getOrigin().getId());
        studentSession.setUserAcc(accountInfo.getOrigin().getStudentNo());
        studentSession.setUserName(accountInfo.getOrigin().getName());
        studentSessionService.saveSession(studentSession);

        return ModelMapperUtil.strictMap(studentSession, AccountSessionInfo.class);
    }

    @Override
    public String getSessionIdPrefix() {
        return studentSessionService.getSessionIdPrefix();
    }

    @Override
    public LoginUserInfoVO.CookieInfo getCookieInfo(String sessionId) {
        return studentSessionService.getCookieInfo(sessionId);
    }

}
