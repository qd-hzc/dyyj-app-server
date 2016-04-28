package com.hzc.top.dao;

import com.hzc.top.model.HsIntegral;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.List;
import java.util.Map;

public interface HsIntegralMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HsIntegral record);

    int insertSelective(HsIntegral record);

    HsIntegral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HsIntegral record);

    int updateByPrimaryKey(HsIntegral record);

    List<HsIntegral> selectListByUidAndType(@Param("userId") Integer uid, @Param("type") int type);

    List<Map> selectSourceList(Integer userId);

    List<Map> selectTargetList(Integer userId);

    /**
     * 根据userId获取用户的积分信息
     * @param userId
     * @return
     */
    List<HsIntegral> selectByUserId(int userId);
}