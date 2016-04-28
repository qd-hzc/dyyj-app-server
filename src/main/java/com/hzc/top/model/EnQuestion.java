package com.hzc.top.model;

import com.hzc.top.util.AppDesUtil;

public class EnQuestion {
    private Integer id;

    private String categorycode;

    private Integer materialid;

    private String type;

    private String knowledgepoint;

    private String chapter;

    private String parserVideoId;

    private Integer deleted;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode == null ? null : categorycode.trim();
    }

    public Integer getMaterialid() {
        return materialid;
    }

    public void setMaterialid(Integer materialid) {
        this.materialid = materialid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getKnowledgepoint() {
        return knowledgepoint;
    }

    public void setKnowledgepoint(String knowledgepoint) {
        this.knowledgepoint = knowledgepoint == null ? null : knowledgepoint.trim();
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter == null ? null : chapter.trim();
    }

    public String getParserVideoId() {
        return parserVideoId;
    }

    public void setParserVideoId(String parserVideoId) {
        this.parserVideoId = parserVideoId == null ? null : parserVideoId.trim();
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return AppDesUtil.jiaMi(this.name);
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "EnQuestion{" +
                "id=" + id +
                ", categorycode='" + categorycode + '\'' +
                ", materialid=" + materialid +
                ", type='" + type + '\'' +
                ", knowledgepoint='" + knowledgepoint + '\'' +
                ", chapter='" + chapter + '\'' +
                ", parserVideoId='" + parserVideoId + '\'' +
                ", deleted=" + deleted +
                ", name='" + name + '\'' +
                '}';
    }
}