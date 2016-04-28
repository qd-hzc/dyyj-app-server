package com.hzc.top.service.app;

import com.hzc.top.model.EnDiscuss;
import com.hzc.top.util.factory.alias.D;

import java.util.List;

/**
 * Created by yinbin on 2015/3/30.
 */
public class EnDiscussService {

    /**
     * 用户对某个题发表评论
     *
     * @param discuss
     * @return
     */
    public boolean save(EnDiscuss discuss) {
        int i = D.enDiscussMapper().insertSelective(discuss);
        return i == 1;
    }

    /**
     * 用户删除某个题目下的自己的评论
     *
     * @param id
     * @return
     */
    public boolean remove(Integer id) {
        int i = D.enDiscussMapper().deleteByPrimaryKey(id);
        return i == 1;
    }

    /**
     * 查询在某个题目下面的所有用户的评论
     *
     * @param userId
     * @param questionId
     * @return
     */
    public List<EnDiscuss> list(Integer userId, Integer questionId) {
        List<EnDiscuss> enDiscusses = D.enDiscussMapper().selectByUserId(userId, questionId);
        return enDiscusses;
    }


}
