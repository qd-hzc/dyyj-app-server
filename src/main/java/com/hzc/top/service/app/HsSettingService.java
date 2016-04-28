package com.hzc.top.service.app;

import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.HsSetting;
import com.hzc.top.util.factory.alias.D;

/**
 * Created by yinbin on 2015/4/3.
 */
@Transaction
public class HsSettingService {


    /**
     * 加载最新的一条设置信息
     *
     * @return
     */
    public HsSetting load() {
        return D.hsSettingMapper().selectTheLast();
    }
}
