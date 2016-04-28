package com.hzc.top.service.app;

import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.HsAnnouncement;
import com.hzc.top.util.factory.alias.D;

import java.util.List;

/**
 * Created by LiuJY on 2015/4/27.
 */
@Transaction
public class HsAnnouncementService {

    /**
     * 查询公告信息
     *
     * @return
     */
    public List<HsAnnouncement> list() {
        return D.hsAnnouncementMapper().selectAll();
    }
}
