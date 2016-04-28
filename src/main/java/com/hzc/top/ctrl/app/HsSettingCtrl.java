package com.hzc.top.ctrl.app;

import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;

/**
 * Created by LiuJY on 2015/4/6.
 */
public class HsSettingCtrl {

    /**
     * 获取最新的积分设置
     */
    public void loadHsSetting(){
        W.writeJsonObject(S.hsSettingService().load());
    }
}
