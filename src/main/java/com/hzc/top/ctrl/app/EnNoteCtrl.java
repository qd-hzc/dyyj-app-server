package com.hzc.top.ctrl.app;

import com.hzc.top.model.EnNote;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;

/**
 * Created by yinbin on 2015/3/30.
 */
public class EnNoteCtrl {

    /**
     * 保存用户的学习笔记到服务器
     */
    public void saveUserNote() {
        EnNote note = W.packBean(EnNote.class);
        boolean b = S.noteService().saveNote(note);
        W.writeJson(b, "");
    }

    /**
     * 用户修改了某个问题对应的笔记
     */
    public void updateUserNote() {
        EnNote enNote = W.packBean(EnNote.class);
        boolean b = S.noteService().updateNote(enNote);
        W.writeJson(b, "");
    }

    /**
     * 查询某个用户的某个题目下的唯一一个笔记
     */
    public void loadUserNote() {
        Integer userId = W.getInteger("userId");
        Integer questionId = W.getInteger("questionId");
        EnNote enNote = S.noteService().loadNote(userId, questionId);
        W.writeJsonObject(enNote);
    }
}
