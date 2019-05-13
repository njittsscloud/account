package com.tss.account.services.teacher.dao;

import com.tss.account.services.teacher.po.TeacherSession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherSessionDao {

    int insert(TeacherSession record);

    TeacherSession findById(Long id);

    int update(TeacherSession record);

    TeacherSession findBySessionId(String sessionId);
}