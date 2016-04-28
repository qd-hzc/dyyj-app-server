package com.hzc.top.dao;


import com.hzc.top.model.EnResolution;

import java.util.List;

public interface EnResolutionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnResolution record);

    int insertSelective(EnResolution record);

    EnResolution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnResolution record);

    int updateByPrimaryKey(EnResolution record);

    /**
     * 根据节id获取其下的所有试题的resolution
     * 参数id：sectionId（节id）
     *
     * @param id
     * @return
     */
    List<EnResolution> selectBySectionId(String id);
}