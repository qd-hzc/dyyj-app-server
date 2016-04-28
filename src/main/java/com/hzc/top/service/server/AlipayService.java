package com.hzc.top.service.server;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.RSA;
import com.alipay.util.AlipayCore;
import com.alipay.util.UtilDate;
import com.hzc.framework.ssh.service.TrancationType;
import com.hzc.framework.ssh.service.Transaction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuJY on 2015/5/18.
 */
@Transaction(jdbc = TrancationType.CLOSE)
public class AlipayService {

    /**
     * 生成订单，返回给app，app调用接口，完成支付
     * 订单格式：
     * service="mobile.securitypay.pay"
     * &partner="2088801273866834"
     * &_input_charset="UTF-8"
     * &out_trade_no="20150513095757"
     * &subject="DCloud项目捐赠"
     * &payment_type="1"
     * &seller_id="payservice@dcloud.io"
     * &total_fee="3380"
     * &body="DCloud致力于打造HTML5最好的移动开发工具，包括终端的Runtime、云端的服务和IDE，同时提供各项配套的开发者服务。"
     * &it_b_pay="1d"
     * &notify_url="http%3A%2F%2Fdemo.dcloud.net.cn%2Fpayment%2Falipay%2Fnotify.php"
     * &show_url="http%3A%2F%2Fwww.dcloud.io%2Fhelloh5%2F"
     * &sign="avLfziNiNcFNrFp0s1zSpsg1N9L02nYm%2FtIwOdxUb4D5oEzTKRNFucgnmyxDRexme2kh5xjqx6ebXKr
     * %2B3ZKlUVzFIsqdQA%2F0ziUcYiN7ad0B%2F0WDimHnLMsV3NYLpzuMLJs5f6OvbfzFiTB50vpmRd276yZsfjDFEmzJqX6cxKc%3D"
     * &sign_type="RSA"
     *
     * @param total_fee
     * @param subject
     * @return
     */
    public String generateOrder(String total_fee, String subject) {
        //把请求参数打包成数组
        Map<String, String> sParam = new HashMap<String, String>();
        sParam.put("service", "mobile.securitypay.pay");
        sParam.put("partner", AlipayConfig.partner);
        sParam.put("_input_charset", AlipayConfig.input_charset);
        sParam.put("out_trade_no", UtilDate.getOrderNum());
        sParam.put("subject", subject);
        sParam.put("payment_type", "1");
        sParam.put("seller_id", "wtyqdtop@126.com");
        sParam.put("total_fee", total_fee);
        sParam.put("body", "欢迎订阅托普学校课程");
        sParam.put("notify_url", "/notify_url.jsp");
        String linkString = AlipayCore.createLinkString(sParam);
        String sign = RSA.sign(linkString, AlipayConfig.private_key, AlipayConfig.input_charset);
        sParam.put("sign", sign);
        sParam.put("sign_type", AlipayConfig.sign_type);
        String order = AlipayCore.createLinkString(sParam);
        return order;
    }
}
