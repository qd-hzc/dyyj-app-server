package com.hzc.top.dao;

import com.hzc.top.model.EnStudytime;

public interface EnStudytimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnStudytime record);

    int insertSelective(EnStudytime record);

    EnStudytime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnStudytime record);

    int updateByPrimaryKey(EnStudytime record);
}