package com.hzc.top.dao;

import com.hzc.top.model.HsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HsCategory record);

    int insertSelective(HsCategory record);

    HsCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HsCategory record);

    int updateByPrimaryKey(HsCategory record);

    List<HsCategory> selectByParentId(Integer parentId);

}