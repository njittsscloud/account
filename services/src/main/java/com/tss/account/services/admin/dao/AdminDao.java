package com.tss.account.services.admin.dao;

import com.tss.account.services.admin.po.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByAccount(String userAccount);
}