package com.hzc.top.ctrl.app;

import com.hzc.top.model.BizAccountingEnroll;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import com.hzc.framework.ssh.controller.WebUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by Felix Yin on 2015/3/19.
 */
public class KjAccountingEnrollCtrl {

    /**
     * 查询报名列表
     * 查询列表包括条件搜索
     */
    public void search() {
        BizAccountingEnroll bean = new BizAccountingEnroll();
        List<BizAccountingEnroll> result = S.accountingEnrollService().search(bean);
        W.writeJsonArray(result);
    }

    /**
     * 代报名
     * 前台传是否代报名完成状态过来，已完成代报名状态值为3；
     * 未完成代报名状态值为4；
     * 根据状态值，同步到数据库
     */
    public void ajaxEnroll() {
        BizAccountingEnroll bean = WebUtil.packBean(BizAccountingEnroll.class);
        Boolean result = S.accountingEnrollService().enrollStatus(bean);
        WebUtil.writeJson(result, "");
    }

    /**
     * 保存会计从业资格证考试的报名信息
     */
    public void signUp() throws IOException {
        BizAccountingEnroll bean = W.packBean(BizAccountingEnroll.class);
        boolean isSave = S.accountingEnrollService().save(bean);
        W.writeJson(isSave, "");
    }
}
