package com.tss.account.services.student.dao;

import com.tss.account.services.student.po.StudentSession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentSessionDao {

    int insert(StudentSession record);

    StudentSession findById(Long id);

    int update(StudentSession record);

}