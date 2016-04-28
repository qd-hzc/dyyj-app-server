package com.hzc.top.ctrl.app;

import com.hzc.top.model.HsAnnouncement;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;

import java.util.List;

/**
 * Created by LiuJY on 2015/4/27.
 */
public class HsAnnouncementCtrl {

    /**
     * 获取系统公告信息
     */
    public void getAnnouncement() {
        List<HsAnnouncement> list = S.hsAnnouncementService().list();
        W.writeJsonArray(list);
    }
}
