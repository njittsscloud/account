package com.tss.account.services.student;

import com.tss.account.interfaces.student.StudentInterface;
import com.tss.account.interfaces.student.vo.UserBaseInfo;
import com.tss.account.services.student.dao.StudentDao;
import com.tss.account.services.student.po.Student;
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

    @Autowired
    private StudentDao studentDao;

    @Override
    public UserBaseInfo getUserBaseInfo(Long id) {
        Student student = studentDao.findById(id);
        if (student != null) {
            UserBaseInfo userBaseInfo = new UserBaseInfo();
            userBaseInfo.setId(student.getId());
            userBaseInfo.setName(student.getName());
            return userBaseInfo;
        }
        return null;
    }
}
