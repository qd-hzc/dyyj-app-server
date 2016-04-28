package com.hzc.top.dao;

import com.hzc.top.model.HsAnnouncement;

import java.util.List;

public interface HsAnnouncementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HsAnnouncement record);

    int insertSelective(HsAnnouncement record);

    HsAnnouncement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HsAnnouncement record);

    int updateByPrimaryKeyWithBLOBs(HsAnnouncement record);

    int updateByPrimaryKey(HsAnnouncement record);

    /**
     * 查询公告信息
     *
     * @return
     */
    List<HsAnnouncement> selectAll();
}