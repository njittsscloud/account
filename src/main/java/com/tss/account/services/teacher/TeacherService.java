package com.tss.account.services.teacher;

import com.tss.account.interfaces.teacher.TeacherInterface;
import com.tss.account.interfaces.vo.UserAuthInfoVO;
import com.tss.account.services.teacher.dao.TeacherDao;
import com.tss.account.services.teacher.po.Teacher;
import com.tss.basic.site.user.annotation.TeacherUser;
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
 * @author: MQG
 * @date: 2018/10/16
 */
@Service
public class TeacherService implements TeacherInterface {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherService.class);

    @Value("${redis.user.teacher.prefix}")
    private String userPrefix;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public TeacherUser getLoginInfo(String userAcc) {
        TeacherUser teacherUser;

        Object temp = redisTemplate.opsForValue().get(userPrefix + userAcc);
        if (temp != null && temp instanceof TeacherUser) {
            teacherUser = (TeacherUser) temp;
        } else {
            Teacher teacher = teacherDao.findByAccount(userAcc);
            TSSAssert.isNotNull(teacher, "账号无效");

            teacherUser = new TeacherUser();
            teacherUser.setTeacherId(teacher.getId());
            teacherUser.setJobNo(teacher.getJobNo());
            teacherUser.setTeacherName(teacher.getName());
            teacherUser.setAcademicId(teacher.getAcademicId());
            teacherUser.setAcademicName(teacher.getAcademicName());
            redisTemplate.opsForValue().set(userPrefix + userAcc, teacherUser);
        }
        return teacherUser;
    }

    @Override
    public UserAuthInfoVO getAuthInfoByUserAcc(String userAcc) {
        Teacher teacher = teacherDao.findByAccount(userAcc);
        if (teacher != null) {
            UserAuthInfoVO userInfo = new UserAuthInfoVO();
            userInfo.setUserAcc(teacher.getJobNo());
            userInfo.setName(teacher.getName());
            userInfo.setPassword(teacher.getPassword());
            Set<String> roles = new HashSet<>(Arrays.asList("TEACHER"));
            userInfo.setRoles(roles);
            // userInfo.setPermissions();
            return userInfo;
        }
        return null;
    }

}
