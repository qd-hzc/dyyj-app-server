package com.hzc.top.dao;

import com.hzc.top.model.BizAccountingEnroll;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BizAccountingEnrollMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizAccountingEnroll record);

    int insertSelective(BizAccountingEnroll record);

    BizAccountingEnroll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizAccountingEnroll record);

    int updateByPrimaryKey(BizAccountingEnroll record);

    List<BizAccountingEnroll> selectPaperList(@Param("ae") BizAccountingEnroll record);

    List<BizAccountingEnroll> selectPaperList(@Param("ae") BizAccountingEnroll record, RowBounds rowBounds);
}