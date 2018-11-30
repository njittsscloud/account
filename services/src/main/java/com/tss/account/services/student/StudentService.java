package com.tss.account.services.student;

import com.tss.account.common.exception.BadUserOrPasswordException;
import com.tss.account.common.exception.DataCheckException;
import com.tss.account.common.util.UserUtil;
import com.tss.account.common.exception.GenSessionFailedException;
import com.tss.account.interfaces.student.StudentInterface;
import com.tss.account.interfaces.student.enums.SessionStatusEnum;
import com.tss.account.interfaces.student.vo.UserBaseInfo;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.account.services.student.dao.StudentDao;
import com.tss.account.services.student.po.Student;
import com.tss.account.services.student.po.StudentSession;
import com.tss.basic.common.util.MD5Util;
import com.tss.basic.common.util.ModelMapperUtil;
import com.tss.basic.site.user.annotation.StudentUser;
import com.tss.basic.site.util.TSSAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * StudentService
 *
 * @author: MQG
 * @date: 2018/10/16
 */
@Service
public class StudentService implements StudentInterface {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentSessionService studentSessionService;

    @Override
    public LoginUserInfoVO doLogin(UserIdentityVO userIdentity) {
        // 账号不存在
        Student student = studentDao.findByAccount(userIdentity.getUserName());
        if (student == null) {
            throw new BadUserOrPasswordException();
        }
        // 用户名或密码错误
        if (!UserUtil.hashPassword(userIdentity.getPassword(), student.getSalt()).equals(student.getPassword())) {
            throw new BadUserOrPasswordException();
        }
        
        StudentSession studentSession = new StudentSession();
        studentSession.setCreateTime(new Date());
        studentSession.setDelFlag(0);
        studentSession.setExpireTime(this.genExpireDate());
        String sessionId = this.genSessionId();
        if (sessionId == null) {
            LOG.error("bad session prefix {}", studentSessionService.getSessionIdPrefix());
            throw new GenSessionFailedException("bad session id prefix.");
        }
        studentSession.setSessionId(genSessionId());
        studentSession.setStatus(SessionStatusEnum.VALID.getId());
        studentSession.setUserId(student.getId());
        studentSession.setUserAcc(student.getStudentNo());
        studentSessionService.saveSession(studentSession);

        LoginUserInfoVO loginUserInfo = sessionUser2LoginUser(studentSession);
        return loginUserInfo;
    }

    protected LoginUserInfoVO sessionUser2LoginUser(StudentSession sessionUser) {
        LoginUserInfoVO retailLoginUserInfo = new LoginUserInfoVO();
        retailLoginUserInfo.setUserId(sessionUser.getUserId());
        retailLoginUserInfo.setUserAcc(sessionUser.getUserAcc());
        retailLoginUserInfo.setCreateTime(sessionUser.getCreateTime());
        retailLoginUserInfo.setExpireDate(sessionUser.getExpireTime());
        retailLoginUserInfo.setSessionId(sessionUser.getSessionId());
        return retailLoginUserInfo;
    }

    /**
     * 生成回话超时时间固定30天
     *
     * @return
     */
    private static Date genExpireDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }

    protected final String genSessionId() {
        UUID uuid = UUID.randomUUID();
        Random random = new Random();
        int r = random.nextInt(10000);

        String prefix = studentSessionService.getSessionIdPrefix();
        if (prefix == null || prefix.length() > 6) {
            return null;
        }
        return prefix + "_" + MD5Util.hex(uuid.toString() + r).toLowerCase();
    }

    @Override
    public StudentUser getLoginInfo(String sessionId) {
        StudentSession studentSession = studentSessionService.findBySessionId(sessionId);
        TSSAssert.isNotNull(studentSession, "session无效");
        Student student = studentDao.findById(studentSession.getUserId());
        TSSAssert.isNotNull(student, "session无效");
        
        StudentUser studentUser = new StudentUser();
        studentUser.setStudentId(studentSession.getUserId());
        studentUser.setStudentNo(studentSession.getUserAcc());
        studentUser.setStudentName(student.getName());
        studentUser.setClassId(student.getClassId());
        studentUser.setClassName(student.getClassName());
        studentUser.setAcademicId(student.getAcademicId());
        studentUser.setAcademicName(student.getAcademicName());
        return studentUser;
    }

    @Override
    public UserBaseInfo getUserBaseInfo(Long id) {
        Student student = studentDao.findById(id);
        if (student == null) {
            throw new DataCheckException("无效的id");
        }
        return ModelMapperUtil.strictMap(student, UserBaseInfo.class);
    }

    
}
