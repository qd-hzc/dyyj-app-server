package com.hzc.top.model;

import java.util.Date;

public class HsSetting {
    private Integer id;

    private Integer applied;

    private Integer itglSignVal;

    private Integer itglSignSwitch;

    private Integer itglRcmdVal;

    private Integer itglRcmdSwitch;

    private Integer itglExchange;

    private Integer categoryVersion;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplied() {
        return applied;
    }

    public void setApplied(Integer applied) {
        this.applied = applied;
    }

    public Integer getItglSignVal() {
        return itglSignVal;
    }

    public void setItglSignVal(Integer itglSignVal) {
        this.itglSignVal = itglSignVal;
    }

    public Integer getItglSignSwitch() {
        return itglSignSwitch;
    }

    public void setItglSignSwitch(Integer itglSignSwitch) {
        this.itglSignSwitch = itglSignSwitch;
    }

    public Integer getItglRcmdVal() {
        return itglRcmdVal;
    }

    public void setItglRcmdVal(Integer itglRcmdVal) {
        this.itglRcmdVal = itglRcmdVal;
    }

    public Integer getItglRcmdSwitch() {
        return itglRcmdSwitch;
    }

    public void setItglRcmdSwitch(Integer itglRcmdSwitch) {
        this.itglRcmdSwitch = itglRcmdSwitch;
    }

    public Integer getItglExchange() {
        return itglExchange;
    }

    public void setItglExchange(Integer itglExchange) {
        this.itglExchange = itglExchange;
    }

    public Integer getCategoryVersion() {
        return categoryVersion;
    }

    public void setCategoryVersion(Integer categoryVersion) {
        this.categoryVersion = categoryVersion;
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