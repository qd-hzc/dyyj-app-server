package com.hzc.top.model;

import com.hzc.top.util.AppDesUtil;

public class EnResolution {
    private Integer id;

    private Integer questionid;

    private String name;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getName() {
//        return name;
        return AppDesUtil.jiaMi(this.name);
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "EnResolution{" +
                "id=" + id +
                ", questionid=" + questionid +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}