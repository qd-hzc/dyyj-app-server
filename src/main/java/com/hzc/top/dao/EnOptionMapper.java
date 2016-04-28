package com.hzc.top.dao;


import com.hzc.top.model.EnOption;

import java.util.List;

public interface EnOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnOption record);

    int insertSelective(EnOption record);

    EnOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnOption record);

    int updateByPrimaryKey(EnOption record);

    /**
     * 根据节id获取该节下的所有试题的选项
     * 参数id：sectionId（节id）
     *
     * @param id
     * @return
     */
    List<EnOption> selectBySectionId(String id);
}