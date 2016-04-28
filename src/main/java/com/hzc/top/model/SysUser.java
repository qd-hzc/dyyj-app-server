package com.hzc.top.model;

import java.util.Date;

public class SysUser {
    private Integer id;

    private String phone;

    private String passwd;

    private Integer status;

    private Integer deleted;

    private Date createTime;

    private Integer online;

    private String key;

    private Integer availableTime;

    private Integer availabletimeStart;

    private Integer availabletimeEnd;

    private Integer remainTime;

    private Integer remainTimeStart;

    private Integer remainTimeEnd;

    private String username;

    private String idcard;

    private Integer age;

    private Integer sex;

    private Date updatetime;

    private Integer updatetimeInt;

    private String recommendPhone;

    private Integer system;

    public Long getUpdatetimeInt() {
        return this.updatetime.getTime();
    }

    public void setUpdatetimeInt(Long updatetimeInt) {
        this.updatetime = new Date(updatetimeInt);
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreatetime() {
        return createTime;
    }

    public void setCreatetime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDeskey() {
        return key;
    }

    public void setDeskey(String key) {
        this.key = key;
    }

    public Integer getAvailableTime() {
        return availableTime;
    }

    public void setAvailabletime(Integer availableTime) {
        this.availableTime = availableTime;
    }

    public Integer getAvailabletime() {
        return availableTime;
    }

    public void setAvailableTime(Integer availableTime) {
        this.availableTime = availableTime;
    }

    public Integer getAvailabletimeStart() {
        return availabletimeStart;
    }

    public void setAvailabletimeStart(Integer availabletimeStart) {
        this.availabletimeStart = availabletimeStart;
    }

    public Integer getAvailabletimeEnd() {
        return availabletimeEnd;
    }

    public void setAvailabletimeEnd(Integer availabletimeEnd) {
        this.availabletimeEnd = availabletimeEnd;
    }

    public Integer getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(Integer remainTime) {
        this.remainTime = remainTime;
    }

    public Integer getRemaintime() {
        return remainTime;
    }

    public void setRemaintime(Integer remainTime) {
        this.remainTime = remainTime;
    }

    public Integer getRemainTimeStart() {
        return remainTimeStart;
    }

    public void setRemainTimeStart(Integer remainTimeStart) {
        this.remainTimeStart = remainTimeStart;
    }

    public Integer getRemainTimeEnd() {
        return remainTimeEnd;
    }

    public void setRemainTimeEnd(Integer remainTimeEnd) {
        this.remainTimeEnd = remainTimeEnd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRecommendPhone() {
        return recommendPhone;
    }

    public void setRecommendPhone(String recommendPhone) {
        this.recommendPhone = recommendPhone;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }
}