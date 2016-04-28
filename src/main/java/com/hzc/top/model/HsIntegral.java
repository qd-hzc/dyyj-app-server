package com.hzc.top.model;

import com.hzc.framework.ssh.controller.validate.anno.NotNull;

import java.util.Date;

public class HsIntegral  {
    private Integer id;

//    @NotNull(message = "用户不能为空")
    private Integer userId;

    private Integer settingId;

    private Integer sourceType;

    private Integer sourceId;

    private Integer targetType;

    private Integer targetId;

    private Integer integralVal;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    // 一下为扩展的新属性
//    private Integer consumeId;
//    private Integer consumeType;
//    private Date consumeCreateTime;
//    private Date consumeUpdateTime;
//    private Date consumeDeleted;
//
//    public Integer getConsumeId() {
//        return consumeId;
//    }
//
//    public void setConsumeId(Integer consumeId) {
//        this.consumeId = consumeId;
//    }
//
//    public Integer getConsumeType() {
//        return consumeType;
//    }
//
//    public void setConsumeType(Integer consumeType) {
//        this.consumeType = consumeType;
//    }
//
//    public Date getConsumeCreateTime() {
//        return consumeCreateTime;
//    }
//
//    public void setConsumeCreateTime(Date consumeCreateTime) {
//        this.consumeCreateTime = consumeCreateTime;
//    }
//
//    public Date getConsumeUpdateTime() {
//        return consumeUpdateTime;
//    }
//
//    public void setConsumeUpdateTime(Date consumeUpdateTime) {
//        this.consumeUpdateTime = consumeUpdateTime;
//    }
//
//    public Date getConsumeDeleted() {
//        return consumeDeleted;
//    }
//
//    public void setConsumeDeleted(Date consumeDeleted) {
//        this.consumeDeleted = consumeDeleted;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getIntegralVal() {
        return integralVal;
    }

    public void setIntegralVal(Integer integralVal) {
        this.integralVal = integralVal;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}