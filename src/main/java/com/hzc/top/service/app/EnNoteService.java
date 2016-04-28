package com.hzc.top.service.app;

import com.hzc.top.model.EnNote;
import com.hzc.top.util.factory.alias.D;
import com.hzc.framework.ssh.service.Transaction;

/**
 * Created by yinbin on 2015/3/30.
 */
@Transaction
public class EnNoteService {

    /**
     * 保存用户的笔记
     *
     * @param note
     * @return
     */
    public boolean saveNote(EnNote note) {
        int i = D.enNoteMapper().insertSelective(note);
        return i == 1;
    }

    /**
     * 用户修改了某个题的笔记
     *
     * @param note
     * @return
     */
    public boolean updateNote(EnNote note) {
        int i = D.enNoteMapper().updateByPrimaryKeySelective(note);
        return i == 1;
    }

    /**
     * 查询某个用户的某个题上做的笔记
     *
     * @param userId
     * @param questionId
     * @return
     */
    public EnNote loadNote(Integer userId, Integer questionId) {
        EnNote enNote = D.enNoteMapper().selectByUserId(userId, questionId);
        return enNote;
    }

}
