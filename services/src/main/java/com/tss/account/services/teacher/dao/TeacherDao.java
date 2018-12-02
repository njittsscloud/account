package com.tss.account.services.teacher.dao;

import com.tss.account.services.teacher.po.Teacher;

public interface TeacherDao {

    int insert(Teacher record);

    Teacher findById(Long id);

    int update(Teacher record);

    Teacher findByAccount(String account);
}