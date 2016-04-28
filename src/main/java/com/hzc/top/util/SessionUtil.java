package com.hzc.top.util;

import com.hzc.top.util.factory.alias.W;

/**
 * Created by LiuJY on 2015/4/29.
 *
 * 记录session中的变量
 *
 */
public class SessionUtil {

    /**
     * 获取userId
     * @return
     */
    public static int getUserId(){
        return (Integer)W.getSession().getAttribute("userId");
    }
}
