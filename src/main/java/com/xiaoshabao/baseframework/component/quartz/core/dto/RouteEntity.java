package com.xiaoshabao.baseframework.component.quartz.core.dto;

import java.util.Date;

public class RouteEntity {
  private Integer workId;
  private Integer startTaskId;
  private Integer endTaskId;
  private String remark;
  private String used;
  private String updateUser;
  private Date updateTime;
  public Integer getWorkId() {
    return workId;
  }
  public void setWorkId(Integer workId) {
    this.workId = workId;
  }
  public Integer getStartTaskId() {
    return startTaskId;
  }
  public void setStartTaskId(Integer startTaskId) {
    this.startTaskId = startTaskId;
  }
  public Integer getEndTaskId() {
    return endTaskId;
  }
  public void setEndTaskId(Integer endTaskId) {
    this.endTaskId = endTaskId;
  }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getUsed() {
    return used;
  }
  public void setUsed(String used) {
    this.used = used;
  }
  public String getUpdateUser() {
    return updateUser;
  }
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }
  public Date getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  
  
}
