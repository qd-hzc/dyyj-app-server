package com.hzc.top.dao;

import com.hzc.top.model.AppVerifyCode;

public interface AppVerifyCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppVerifyCode record);

    int insertSelective(AppVerifyCode record);

    AppVerifyCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVerifyCode record);

    int updateByPrimaryKey(AppVerifyCode record);

    AppVerifyCode selectByPhone(String phone);
}