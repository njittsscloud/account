package com.tss.account.services.teacher;

import com.tss.account.interfaces.teacher.TeacherInterface;
import com.tss.account.services.teacher.dao.TeacherDao;
import com.tss.account.services.teacher.po.Teacher;
import com.tss.account.services.teacher.po.TeacherSession;
import com.tss.basic.site.user.annotation.TeacherUser;
import com.tss.basic.site.util.TSSAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
