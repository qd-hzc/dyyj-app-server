package com.hzc.top.ctrl.app;

import com.hzc.top.model.HsConsume;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;

import java.util.List;

/**
 * Created by yinbin on 2015/4/26.
 */
public class HsConsumeCtrl {

    /**
     * 查询我的消费记录，按照时间倒序
     */
    public void listMyConsume() {
        Integer userId = W.getInteger("userId");
        List<HsConsume> list = S.hsConsumeService().list(userId);
        W.writeJsonArray(list);
    }

}
