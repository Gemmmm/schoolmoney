package com.tc.sm.model;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class TiancekjStudent {
    @Id
    private Integer id;

    private String sname;

    private Integer gradeid;

    private Integer classid;

    private String phone;

    private String city;

    private String schoolName;

    private Integer schoolType;

    private Date createTime;

    private String subjcetCtj;

    private BigDecimal subjectCtjMoney;

    private String subjectYun;

    private BigDecimal subjectYunMoney;

    private Integer ctjIsPay;

    private Integer yunIsPay;

    private String sno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Integer getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSubjcetCtj() {
        return subjcetCtj;
    }

    public void setSubjcetCtj(String subjcetCtj) {
        this.subjcetCtj = subjcetCtj == null ? null : subjcetCtj.trim();
    }

    public BigDecimal getSubjectCtjMoney() {
        return subjectCtjMoney;
    }

    public void setSubjectCtjMoney(BigDecimal subjectCtjMoney) {
        this.subjectCtjMoney = subjectCtjMoney;
    }

    public String getSubjectYun() {
        return subjectYun;
    }

    public void setSubjectYun(String subjectYun) {
        this.subjectYun = subjectYun == null ? null : subjectYun.trim();
    }

    public BigDecimal getSubjectYunMoney() {
        return subjectYunMoney;
    }

    public void setSubjectYunMoney(BigDecimal subjectYunMoney) {
        this.subjectYunMoney = subjectYunMoney;
    }

    public Integer getCtjIsPay() {
        return ctjIsPay;
    }

    public void setCtjIsPay(Integer ctjIsPay) {
        this.ctjIsPay = ctjIsPay;
    }

    public Integer getYunIsPay() {
        return yunIsPay;
    }

    public void setYunIsPay(Integer yunIsPay) {
        this.yunIsPay = yunIsPay;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }
}