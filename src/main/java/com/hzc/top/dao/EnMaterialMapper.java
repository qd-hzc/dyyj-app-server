package com.hzc.top.dao;


import com.hzc.top.model.EnMaterial;
import com.hzc.top.model.EnMaterialWithBLOBs;

import java.util.List;

public interface EnMaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnMaterialWithBLOBs record);

    int insertSelective(EnMaterialWithBLOBs record);

    EnMaterialWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnMaterialWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EnMaterialWithBLOBs record);

    int updateByPrimaryKey(EnMaterial record);

    /**
     * 根据节id获取其下的所有试题的material
     * 参数id : sectionId
     *
     * @param id
     * @return
     */
    List<EnMaterialWithBLOBs> selectBySectionId(String id);
}