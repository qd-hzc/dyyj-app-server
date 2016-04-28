package com.hzc.top.dao;

import com.hzc.top.model.LpCategory;

public interface LpCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LpCategory record);

    int insertSelective(LpCategory record);

    LpCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LpCategory record);

    int updateByPrimaryKey(LpCategory record);
}