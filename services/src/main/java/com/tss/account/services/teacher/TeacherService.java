package com.tss.account.services.teacher;

import com.tss.account.common.exception.BadUserOrPasswordException;
import com.tss.account.common.exception.GenSessionFailedException;
import com.tss.account.common.util.UserUtil;
import com.tss.account.interfaces.enums.SessionStatusEnum;
import com.tss.account.interfaces.teacher.TeacherInterface;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserIdentityVO;
import com.tss.account.services.teacher.dao.TeacherDao;
import com.tss.account.services.teacher.po.Teacher;
import com.tss.account.services.teacher.po.TeacherSession;
import com.tss.basic.common.util.MD5Util;
import com.tss.basic.site.user.annotation.TeacherUser;
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
 * @author: MQG
 * @date: 2018/10/16
 */
@Service
public class TeacherService implements TeacherInterface {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherService.class);

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherSessionService teacherSessionService;

    @Override
    public LoginUserInfoVO doLogin(UserIdentityVO userIdentity) {
        // 账号不存在
        Teacher teacher = teacherDao.findByAccount(userIdentity.getUserName());
        if (teacher == null) {
            throw new BadUserOrPasswordException();
        }
        // 用户名或密码错误
        if (!UserUtil.hashPassword(userIdentity.getPassword(), teacher.getSalt()).equals(teacher.getPassword())) {
            throw new BadUserOrPasswordException();
        }

        TeacherSession teacherSession = new TeacherSession();
        teacherSession.setCreateTime(new Date());
        teacherSession.setDelFlag(0);
        teacherSession.setExpireTime(this.genExpireDate());
        String sessionId = this.genSessionId();
        if (sessionId == null) {
            LOG.error("bad session prefix {}", teacherSessionService.getSessionIdPrefix());
            throw new GenSessionFailedException("bad session id prefix.");
        }
        teacherSession.setSessionId(genSessionId());
        teacherSession.setStatus(SessionStatusEnum.VALID.getId());
        teacherSession.setUserId(teacher.getId());
        teacherSession.setUserAcc(teacher.getJobNo());
        teacherSession.setUserName(teacher.getName());
        teacherSessionService.saveSession(teacherSession);

        LoginUserInfoVO loginUserInfo = sessionUser2LoginUser(teacherSession);
        return loginUserInfo;
    }

    protected LoginUserInfoVO sessionUser2LoginUser(TeacherSession sessionUser) {
        LoginUserInfoVO retailLoginUserInfo = new LoginUserInfoVO();
        retailLoginUserInfo.setUserId(sessionUser.getUserId());
        retailLoginUserInfo.setUserAcc(sessionUser.getUserAcc());
        retailLoginUserInfo.setName(sessionUser.getUserName());
        retailLoginUserInfo.setCreateTime(sessionUser.getCreateTime());
        retailLoginUserInfo.setExpireDate(sessionUser.getExpireTime());
        retailLoginUserInfo.setSessionId(sessionUser.getSessionId());
        return retailLoginUserInfo;
    }

    /**
     * 生成会话超时时间30天
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

        String prefix = teacherSessionService.getSessionIdPrefix();
        if (prefix == null || prefix.length() > 6) {
            return null;
        }
        return prefix + "_" + MD5Util.hex(uuid.toString() + r).toLowerCase();
    }

    @Override
    public TeacherUser getLoginInfo(String sessionId) {
        TeacherSession teacherSession = teacherSessionService.findBySessionId(sessionId);
        TSSAssert.isNotNull(teacherSession, "session无效");
        Teacher teacher = teacherDao.findById(teacherSession.getUserId());
        TSSAssert.isNotNull(teacher, "session无效");

        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setTeacherId(teacher.getId());
        teacherUser.setJobNo(teacher.getJobNo());
        teacherUser.setTeacherName(teacher.getName());
        teacherUser.setAcademicId(teacher.getAcademicId());
        teacherUser.setAcademicName(teacher.getAcademicName());
        return teacherUser;
    }

}
