package com.hzc.top.ctrl.app;

import com.hzc.top.model.EnStudytime;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;

/**
 * Created by yinbin on 2015/3/30.
 */
public class EnStudytimeCtrl {

    /**
     * 保存用户在某个节中停留的时间
     */
    public void saveStudyTime() {
        EnStudytime enStudytime = W.packBean(EnStudytime.class);
        boolean isSave = S.studytimeService().save(enStudytime);
        W.writeJson(isSave, "");
    }
}
