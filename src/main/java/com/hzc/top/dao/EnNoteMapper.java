package com.hzc.top.dao;

import com.hzc.top.model.EnNote;
import org.apache.ibatis.annotations.Param;

public interface EnNoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnNote record);

    int insertSelective(EnNote record);

    EnNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnNote record);

    int updateByPrimaryKey(EnNote record);

    EnNote selectByUserId(@Param("userId") Integer userId, @Param("questionId") Integer questionId);
}