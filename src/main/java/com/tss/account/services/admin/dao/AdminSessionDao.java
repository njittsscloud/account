package com.tss.account.services.admin.dao;

import com.tss.account.services.admin.po.AdminSession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminSessionDao {
    int deleteByPrimaryKey(Long id);

    int insert(AdminSession record);

    int insertSelective(AdminSession record);

    AdminSession selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminSession record);

    int updateByPrimaryKey(AdminSession record);
    
    AdminSession findBySessionId(String sessionId);
}