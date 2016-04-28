package com.hzc.top.ctrl.server;

import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import org.apache.commons.lang.StringUtils;

/**
 * Created by LiuJY on 2015/5/13.
 */
public class AlipayCtrl {

    /**
     * 托普助学系统
     * 根据支付钱和商品名称
     * 返回支付请求，app根据支付请求完成支付
     */
    public void payment(){
        String total_fee = W.getString("total");
        String subject = W.getString("subject");
        String appid = W.getString("appid");
        if (StringUtils.isBlank(total_fee) || StringUtils.isBlank(subject)) {
            throw new IllegalArgumentException("total_fee or subject is wrong");
        }
        String result = S.alipayService().generateOrder(total_fee,subject);
        W.writeText(result);
    }
}
