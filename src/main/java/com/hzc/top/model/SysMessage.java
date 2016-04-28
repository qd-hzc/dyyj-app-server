package com.hzc.top.model;

public class SysMessage {
    private Integer id;

    private String title;

    private String content;

    private String datetime;

    private Integer status;

    private Integer deleted;

    private String sysMessagecol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime == null ? null : datetime.trim();
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

    public String getSysMessagecol() {
        return sysMessagecol;
    }

    public void setSysMessagecol(String sysMessagecol) {
        this.sysMessagecol = sysMessagecol == null ? null : sysMessagecol.trim();
    }
}