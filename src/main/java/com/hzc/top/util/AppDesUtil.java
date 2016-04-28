package com.hzc.top.util;

import com.hzc.framework.util.DesUtil;

/**
 * Created by yinbin on 2015/3/28.
 */
public class AppDesUtil {

    public static String jiaMi(String content) {
        String s = new DesUtil().strEnc(content, "1", "2", "3");
        System.out.println(s);
        return s;
    }

}
