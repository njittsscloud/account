package com.tss.account.services.teacher.dao;

import com.tss.account.services.teacher.po.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherDao {

    int insert(Teacher record);

    Teacher findById(Long id);

    int update(Teacher record);

    Teacher findByAccount(String account);
}