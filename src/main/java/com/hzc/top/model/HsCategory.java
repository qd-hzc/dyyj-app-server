package com.hzc.top.model;

import java.util.ArrayList;
import java.util.Date;

public class HsCategory {

    /**
     * 对接sencha touch的TreeStore
     */
//    private ArrayList<HsCategory> items;

//    private String text;

    /**
     * 是否是叶子节点
     */
//    private boolean leaf;

    /**
     * 用户的积分
     */
//    private Integer integral;

//    public Integer getIntegral() {
//        return integral;
//    }
//
//    public void setIntegral(Integer integral) {
//        this.integral = integral;
//    }

//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }

//    public boolean isLeaf() {
//        return leaf;
//    }
//
//    public void setLeaf(boolean leaf) {
//        this.leaf = leaf;
//    }

//    public ArrayList<HsCategory> getItems() {
//        return items;
//    }
//
//    public void setItems(ArrayList<HsCategory> items) {
//        this.items = items;
//    }

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer type;

    private Float studyHours;

    private Integer studyUnit;

    private Double price;

    private String classDescr;

    private String majorDescr;

    private String subject;

    private Integer examFee;

    private Integer tuitionFee;

    private Integer bookFee;

    private Integer payMethod;

    private Integer integralVal;

    private Integer integralSwitch;

    private Date startDate;

    private Date endDate;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private Integer studyMethod;

    private Integer leaf;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
//        this.text =
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getStudyHours() {
        return studyHours;
    }

    public void setStudyHours(Float studyHours) {
        this.studyHours = studyHours;
    }

    public Integer getStudyUnit() {
        return studyUnit;
    }

    public void setStudyUnit(Integer studyUnit) {
        this.studyUnit = studyUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getClassDescr() {
        return classDescr;
    }

    public void setClassDescr(String classDescr) {
        this.classDescr = classDescr == null ? null : classDescr.trim();
    }

    public String getMajorDescr() {
        return majorDescr;
    }

    public void setMajorDescr(String majorDescr) {
        this.majorDescr = majorDescr == null ? null : majorDescr.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getExamFee() {
        return examFee;
    }

    public void setExamFee(Integer examFee) {
        this.examFee = examFee;
    }

    public Integer getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(Integer tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public Integer getBookFee() {
        return bookFee;
    }

    public void setBookFee(Integer bookFee) {
        this.bookFee = bookFee;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getIntegralVal() {
        return integralVal;
    }

    public void setIntegralVal(Integer integralVal) {
        this.integralVal = integralVal;
    }

    public Integer getIntegralSwitch() {
        return integralSwitch;
    }

    public void setIntegralSwitch(Integer integralSwitch) {
        this.integralSwitch = integralSwitch;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Integer getStudyMethod() {
        return studyMethod;
    }

    public void setStudyMethod(Integer studyMethod) {
        this.studyMethod = studyMethod;
    }

    public Integer getLeaf() {
        return leaf;
    }

    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }
}