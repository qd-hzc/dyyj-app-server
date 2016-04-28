package com.hzc.top.dao;

import com.hzc.top.model.SysVerifycode;

public interface SysVerifycodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysVerifycode record);

    int insertSelective(SysVerifycode record);

    SysVerifycode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysVerifycode record);

    int updateByPrimaryKey(SysVerifycode record);
}