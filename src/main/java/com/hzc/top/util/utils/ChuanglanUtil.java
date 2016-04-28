package com.hzc.top.util.utils;

import com.bcloud.msg.http.HttpSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by LiuJY on 2015/5/22.
 */
public class ChuanglanUtil {

    public static void main(String[] args) {
        System.out.println("2");
        String uri = "http://222.73.117.158/msg/";
        String account = "jiekou-clcs-04";
        String pswd = "Tch147369";
        String mobiles = "15335320821";
        String content = "亲爱的用户，您的验证码1234";
        boolean needstatus = false;
        String product = null;
        String extno = null;
        try {
            String s = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
            System.out.println(s);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static String sentSms(String phone) {
        System.out.println("2");
        String uri = "http://222.73.117.158/msg/";
        String account = "jiekou-clcs-04";
        String pswd = "Tch147369";
        String mobiles = phone;
        String code = generateWord();
        String content = "亲爱的用户，您的验证码：" + code + ".请完成注册";
        boolean needstatus = false;
        String product = null;
        String extno = null;
        try {
            String s = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
            System.out.println(s);
            return code;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    /**
     * 产生随机的6位数字字符串
     *
     * @return
     */
    private static String generateWord() {
        int length = 6;
        String[] beforeShuffle = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
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
