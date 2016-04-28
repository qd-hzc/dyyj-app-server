package com.hzc.top.ctrl;

import com.hzc.top.util.factory.alias.W;

/**
 * Created by yinbin on 2015/3/27.
 */
public class CommonCtrl {

    /**
     * 公用的页面上直接跳转到某个jsp的方法
     */
    public void goTo() {
        W.forward(W.getString(W.JSP_PATH));
    }

}
