package com.hzc.top.dao;

import com.hzc.top.model.EnDiscuss;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnDiscussMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnDiscuss record);

    int insertSelective(EnDiscuss record);

    EnDiscuss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnDiscuss record);

    int updateByPrimaryKey(EnDiscuss record);

    List<EnDiscuss> selectByUserId(@Param("userId") Integer userId, @Param("questionId") Integer questionId);
}