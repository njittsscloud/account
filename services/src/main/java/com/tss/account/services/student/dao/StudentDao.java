package com.tss.account.services.student.dao;

import com.tss.account.services.student.po.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao {
    int deleteById(Long id);

    int insert(Student student);

    Student findById(Long id);

    int update(Student record);
}