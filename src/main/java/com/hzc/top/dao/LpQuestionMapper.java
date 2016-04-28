package com.hzc.top.dao;

import com.hzc.top.model.LpQuestion;
import com.hzc.top.vo.AnswerCardOptimizeVO;
import com.hzc.top.vo.AnswerCardVO;
import com.hzc.top.vo.QuestionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LpQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LpQuestion record);

    int insertSelective(LpQuestion record);

    LpQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LpQuestion record);

    int updateByPrimaryKey(LpQuestion record);

    /**
     * 根据userId
     * 获取学习页答题卡内容
     * 包括：questionId，答题次数，错误次数，试题类型
     *
     * @param userId
     * @return
     */
    List<AnswerCardVO> selectForAnswerCardList(@Param("userId") int userId);


    /**
     * 根据questionId查询用户的试题
     * 包括：questionId，答题次数，错误次数，试题类型
     *
     * @param questionId
     * @param userId
     * @return
     */
    QuestionVO selectByPrimaryKeyForUser(@Param("questionId") int questionId, @Param("userId") int userId);

    /**
     * 分页查询所有试题
     *
     * @param currentNum
     * @param pageSize
     * @return
     */
    List<QuestionVO> selectAllQuestionForLimit(@Param("currentNum") Integer currentNum, @Param("pageSize") Integer pageSize);

    /**
     * 根据试题类型，获取试题
     *
     * @param type
     * @return
     */
    List<AnswerCardVO> selectByType(@Param("userId") int userId, @Param("type") String type);

    /**
     * 根据试题类型，获取试题 优化版本
     *
     * @param type
     * @return
     */
    List<Map> selectByTypeOptimize(@Param("userId") int userId, @Param("type") String type);
}