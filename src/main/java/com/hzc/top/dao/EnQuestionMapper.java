package com.hzc.top.dao;


import com.hzc.top.model.EnQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnQuestion record);

    int insertSelective(EnQuestion record);

    EnQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnQuestion record);

    int updateByPrimaryKeyWithBLOBs(EnQuestion record);

    int updateByPrimaryKey(EnQuestion record);

    /**
     * 根据节code获取节下的所有试题
     * 参数categoryCode
     * 返回List<EnQuestion>
     *
     * @param code
     * @return
     */
    List<EnQuestion> selectByCode(@Param("code")String code);
}