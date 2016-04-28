package com.hzc.top.ctrl.app;

import com.hzc.top.model.EnDiscuss;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;

import java.util.List;

/**
 * Created by yinbin on 2015/3/30.
 */
public class EnDiscussCtrl {


    /**
     * 添加一个评论到某个题
     */
    public void addDiscuss() {
        EnDiscuss enDiscuss = W.packBean(EnDiscuss.class);
        boolean isSave = S.discussService().save(enDiscuss);
        W.writeJson(isSave, "");
    }

    /**
     * 删除某个用户的评论
     */
    public void removeDiscuss() {
        Integer id = W.getInteger("id");
        boolean isRemove = S.discussService().remove(id);
        W.writeJson(isRemove, "");
    }

    /**
     * 查询某个题目下所有用户的评论信息
     *
     * @TODO 因为现在用户较少，所以查询全部信息，没有进行分页，以后需要改成：”查看更多“的滚动分页形式
     */
    public void listDiscuss() {
        Integer userId = W.getInteger("userId");
        Integer questionId = W.getInteger("questionId");
        List<EnDiscuss> list = S.discussService().list(userId, questionId);
        W.writeJsonArray(list);
    }
}
