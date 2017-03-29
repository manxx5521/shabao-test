package com.xiaoshabao.baseframework.component.quartz.core.dto;

import java.util.Date;

public class QuartzWorkEntity {

  private Integer workId;

  private String workName;

  private String remark;

  private Date startTime;

  private Date endTime;

  private String workType;

  private Integer exeNum;

  private Integer expMonth;

  private Integer expDay;

  private Integer expHours;

  private Integer expMinutes;

  private Integer expSeconds;

  private String expression;

  private String status;

  private String sysType;

  private String updateTag;

  private String updateUser;

  private String updateTime;

  public Integer getWorkId() {
    return workId;
  }

  public void setWorkId(Integer workId) {
    this.workId = workId;
  }

  public String getWorkName() {
    return workName;
  }

  public void setWorkName(String workName) {
    this.workName = workName;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSysType() {
    return sysType;
  }

  public void setSysType(String sysType) {
    this.sysType = sysType;
  }

  public String getUpdateTag() {
    return updateTag;
  }

  public void setUpdateTag(String updateTag) {
    this.updateTag = updateTag;
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public int getExeNum() {
    return exeNum;
  }

  public void setExeNum(int exeNum) {
    this.exeNum = exeNum;
  }

  public String getWorkType() {
    return workType;
  }

  public void setWorkType(String workType) {
    this.workType = workType;
  }

  public Integer getExpMonth() {
    return expMonth;
  }

  public void setExpMonth(Integer expMonth) {
    this.expMonth = expMonth;
  }

  public Integer getExpHours() {
    return expHours;
  }

  public Integer getExpDay() {
    return expDay;
  }

  public void setExpDay(Integer expDay) {
    this.expDay = expDay;
  }

  public void setExpHours(Integer expHours) {
    this.expHours = expHours;
  }

  public Integer getExpSeconds() {
    return expSeconds;
  }

  public void setExpSeconds(Integer expSeconds) {
    this.expSeconds = expSeconds;
  }

  public void setExeNum(Integer exeNum) {
    this.exeNum = exeNum;
  }

  public Integer getExpMinutes() {
    return expMinutes;
  }

  public void setExpMinutes(Integer expMinutes) {
    this.expMinutes = expMinutes;
  }

}
