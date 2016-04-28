package com.hzc.top.dao;

import com.hzc.top.model.HsSetting;

public interface HsSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HsSetting record);

    int insertSelective(HsSetting record);

    HsSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HsSetting record);

    int updateByPrimaryKey(HsSetting record);

    /**
     * 获取最新的一条设置信息
     * @return
     */
    HsSetting selectTheLast();

}