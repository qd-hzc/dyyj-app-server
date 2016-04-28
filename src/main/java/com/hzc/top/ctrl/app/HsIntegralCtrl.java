package com.hzc.top.ctrl.app;

import com.hzc.top.model.HsIntegral;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinbin on 2015/4/7.
 */
public class HsIntegralCtrl {

    /**
     * 积分来源记录
     */
    public void sourceList() {
        Integer userId = W.getInteger("userId");
        Integer type = W.getInteger("type");
        System.out.println("----------------->" + type);
        Map<String, Object> root = new HashMap<String, Object>();
        List<Map> hsIntegrals = null;
        if (type == 1) {
            hsIntegrals = S.hsIntegralService().sourceList(userId);
        } else if (type == 2) {
            hsIntegrals = S.hsIntegralService().targetList(userId);
        }
        root.put("root", hsIntegrals);
        root.put("success", true);
        W.writeJsonObject(root);
    }

    /**
     * 积分支出记录
     */
    public void targetList() {

    }


}
