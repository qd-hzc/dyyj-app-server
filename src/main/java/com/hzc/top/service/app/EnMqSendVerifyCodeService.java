package com.hzc.top.service.app;

import com.hzc.framework.util.PropertiesUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by LiuJY on 2015/3/3.
 */
public class EnMqSendVerifyCodeService {

    /**
     * 如果调用短信接口成功则返回验证码<br/>
     * 否则返回null
     *
     * @param phone
     * @return
     * @throws JMSException
     */
    public static String sendMessage(String phone) throws JMSException {
        String code = generateWord();
        Connection connection = null;
        try {
            String url = PropertiesUtil.getProperties("mqserverurl");
            String QUEUE_NAME = PropertiesUtil.getProperties("mqqueuename");

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(destination);
            MapMessage mapMessage = session.createMapMessage();

            mapMessage.setString("phone", phone);
            mapMessage.setString("code", code);

            producer.send(mapMessage);
            System.out.println("验证码已经发送");
        } catch (Exception e) {
            return null;
        } finally {
            connection.close();
        }
        return code;
    }

    /**
     * 获取随机的6位数字字符串
     *
     * @return
     */
    private static String generateWord() {
        int length = 6;
        String[] beforeShuffle = new String[]{"1", "2", "3", "4", "5", "6", "7",
                "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        return afterShuffle.substring(2, 2 + length);
    }

}
