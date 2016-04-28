package com.hzc.top.model;

import com.hzc.framework.ssh.controller.validate.anno.NotNull;

import java.util.Date;

public class BizAccountingEnroll {
    private Integer id;

    @NotNull(message = "姓名不能为空")
    private String name;

    @NotNull(message = "身份证号码不能为空")
    private String code;

    @NotNull(message = "民族不能为空")
    private Integer nation;

    @NotNull(message = "性别不能为空")
    private Integer sex;

    @NotNull(message = "电话不能为空")
    private String phone;

    private String photo;

    @NotNull(message = "电子邮箱不能为空")
    private String emaill;

    private String diploma;

    private String major;

    private Date graduateDate;

    private String certificateNum;

    private String school;

    private String examSubject;

    private Integer place;

    private String company;

    private String companyPhone;

    private String companyAddress;

    private String remarks;

    private Integer paymentOk;

    private Integer paymentMoney;

    private Date paymentTime;

    private String paymentLog;

    private Integer status;

    private Date createTime;

    @NotNull(message = "登录密码不能为空")
    private String password;

    @NotNull(message = "工作单位不能为空")
    private Integer hasCompany;

    @NotNull(message = "居住地址不能为空")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHasCompany() {
        return hasCompany;
    }

    public void setHasCompany(Integer hasCompany) {
        this.hasCompany = hasCompany;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill == null ? null : emaill.trim();
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma == null ? null : diploma.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum == null ? null : certificateNum.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject == null ? null : examSubject.trim();
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getPaymentOk() {
        return paymentOk;
    }

    public void setPaymentOk(Integer paymentOk) {
        this.paymentOk = paymentOk;
    }

    public Integer getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(Integer paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentLog() {
        return paymentLog;
    }

    public void setPaymentLog(String paymentLog) {
        this.paymentLog = paymentLog == null ? null : paymentLog.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}