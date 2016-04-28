package com.hzc.top.dao;

import com.hzc.top.model.HsConsume;

import java.util.List;

public interface HsConsumeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HsConsume record);

    int insertSelective(HsConsume record);

    HsConsume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HsConsume record);

    int updateByPrimaryKey(HsConsume record);

    List<HsConsume> selectListByUserId(Integer userId);
}