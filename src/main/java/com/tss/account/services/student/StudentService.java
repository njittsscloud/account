package com.tss.account.services.student;

import com.tss.account.common.exception.DataCheckException;
import com.tss.account.interfaces.student.StudentInterface;
import com.tss.account.interfaces.student.vo.UserBaseInfo;
import com.tss.account.interfaces.vo.LoginUserInfoVO;
import com.tss.account.interfaces.vo.UserAuthInfoVO;
import com.tss.account.services.student.dao.StudentDao;
import com.tss.account.services.student.po.Student;
import com.tss.account.services.student.po.StudentSession;
import com.tss.basic.common.util.ModelMapperUtil;
import com.tss.basic.site.user.annotation.StudentUser;
import com.tss.basic.site.util.TSSAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * StudentService
 *
 * @author: MQG
 * @date: 2018/10/16
 */
@Service
public class StudentService implements StudentInterface {
    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    @Value("${redis.user.student.prefix}")
    private String userPrefix;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentSessionService studentSessionService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public StudentUser getLoginInfo(String userAcc) {
        StudentUser studentUser;
        // redis缓存获取
        Object temp = redisTemplate.opsForValue().get(userPrefix + userAcc);
        if (temp != null && temp instanceof StudentUser) {
            studentUser = (StudentUser) temp;
        } else {
            Student student = studentDao.findByAccount(userAcc);
            TSSAssert.isNotNull(student, "账号无效");

            studentUser = new StudentUser();
            studentUser.setStudentId(student.getId());
            studentUser.setStudentNo(student.getStudentNo());
            studentUser.setStudentName(student.getName());
            studentUser.setClassId(student.getClassId());
            studentUser.setClassName(student.getClassName());
            studentUser.setAcademicId(student.getAcademicId());
            studentUser.setAcademicName(student.getAcademicName());
            redisTemplate.opsForValue().set(userPrefix + userAcc, studentUser);
        }
        return studentUser;
    }

    @Override
    public UserBaseInfo getUserBaseInfoBySessionId(String sessionId) {
        StudentSession studentSession = studentSessionService.findBySessionId(sessionId);
        if (studentSession == null) {
            throw new DataCheckException("session失效");
        }

        Student student = studentDao.findById(studentSession.getUserId());
        if (student == null) {
            throw new DataCheckException("无效的用户id");
        }

        UserBaseInfo userBaseInfo = ModelMapperUtil.strictMap(student, UserBaseInfo.class);
        userBaseInfo.setStuNo(student.getStudentNo());
        return userBaseInfo;
    }

    @Override
    public UserAuthInfoVO getAuthInfoByUserAcc(String userAcc) {
        Student student = studentDao.findByAccount(userAcc);
        if (student != null) {
            UserAuthInfoVO userInfo = new UserAuthInfoVO();
            userInfo.setUserAcc(student.getStudentNo());
            userInfo.setName(student.getName());
            userInfo.setPassword(student.getPassword());
            Set<String> roles = new HashSet<>(Arrays.asList("STUDENT"));
            userInfo.setRoles(roles);
            // userInfo.setPermissions();
            return userInfo;
        }
        return null;
    }

}