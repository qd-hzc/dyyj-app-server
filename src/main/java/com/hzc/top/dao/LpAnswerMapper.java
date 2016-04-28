package com.hzc.top.dao;

import com.hzc.top.model.LpAnswer;
import org.apache.ibatis.annotations.Param;

public interface LpAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LpAnswer record);

    int insertSelective(LpAnswer record);

    LpAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LpAnswer record);

    int updateByPrimaryKey(LpAnswer record);

    /**
     * 更新用户的错题次数
     *
     * @param answer
     */
    void updateAnswerInfoByUserIdAndQuestionId(LpAnswer answer);

    /**
     * 根据userId 和 questionId 获取用户的答题记录
     *
     * @param userId
     * @param questionId
     * @return
     */
    LpAnswer selectByUserIdAndQuestionId(@Param("userId") int userId, @Param("questionId") int questionId);

}