package com.tss.account.services.student;

import com.tss.account.common.exception.DataCheckException;
import com.tss.account.interfaces.student.StudentInterface;
import com.tss.account.interfaces.student.vo.UserBaseInfo;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.services.student.dao.StudentDao;
import com.tss.account.services.student.po.Student;
import com.tss.account.services.student.po.StudentSession;
import com.tss.basic.common.util.ModelMapperUtil;
import com.tss.basic.site.user.annotation.StudentUser;
import com.tss.basic.site.util.TSSAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public LoginUserInfoVO getLoginInfoByUserAcc(String userAcc) {
        Student student = studentDao.findByAccount(userAcc);
        if (student != null) {
            return ModelMapperUtil.strictMap(student, LoginUserInfoVO.class);
        }
        return null;
    }

}
