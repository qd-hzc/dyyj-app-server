package com.hzc.top.dao;

import com.hzc.top.model.PufaImport;

import java.util.List;

public interface PufaImportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PufaImport record);

    int insertSelective(PufaImport record);

    PufaImport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PufaImport record);

    int updateByPrimaryKey(PufaImport record);

    List<PufaImport> selectAll();

}