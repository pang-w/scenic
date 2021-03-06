package com.itmaoo.oa.entity.po;

import java.util.Date;

/**
 * 实体类 - 商品
 **/

public class ProductPo extends BasePo {

  private static final long serialVersionUID = 4858058186018438872L;
  /**
   * 登记查询的信息必须包括:姓名，性别，年龄，检测结果，病理唯一编号，送检医生，送检科室，送检日期，报告日期，样本类型，ID
   */
  private Integer id;
  private String name;
  private String sex;
  private String age;
  private String testResault;
  private String caseId;
  private String doctor;
  private String department;
  private Date sendDate;
  private Date reportDate;
  private String testType;
  private String description;
  
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
    this.name = name;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getAge() {
    return age;
  }
  public void setAge(String age) {
    this.age = age;
  }
  public String getTestResault() {
    return testResault;
  }
  public void setTestResault(String testResault) {
    this.testResault = testResault;
  }
  public String getCaseId() {
    return caseId;
  }
  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }
  public String getDoctor() {
    return doctor;
  }
  public void setDoctor(String doctor) {
    this.doctor = doctor;
  }

 
  public String getDepartment() {
    return department;
  }
  public void setDepartment(String department) {
    this.department = department;
  }
  public Date getSendDate() {
    return sendDate;
  }
  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }
  public Date getReportDate() {
    return reportDate;
  }
  public void setReportDate(Date reportDate) {
    this.reportDate = reportDate;
  }
  public String getTestType() {
    return testType;
  }
  public void setTestType(String testType) {
    this.testType = testType;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

}