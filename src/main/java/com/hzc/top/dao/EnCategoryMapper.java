package com.hzc.top.dao;


import com.hzc.top.model.EnCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnCategory record);

    int insertSelective(EnCategory record);

    EnCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnCategory record);

    int updateByPrimaryKey(EnCategory record);

    /**
     * 查询所有category
     * @return
     */
    List<EnCategory> selectAll();

    /**
     * 根据code获取category
     *
     * @param code
     * @param codeLength
     * @return
     */
    List<EnCategory> selectByCode(@Param("code")String code, @Param("codeLength")int codeLength);
}