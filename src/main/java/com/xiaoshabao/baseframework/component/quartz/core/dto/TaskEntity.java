package com.xiaoshabao.baseframework.component.quartz.core.dto;

import java.util.Date;

public class TaskEntity {
  
  private Integer taskId;
  private String taskName;
  private Integer taskType;
  private String content;
  private int parallel;
  private int used;
  private String updateUser;
  private Date updateTime;
  public Integer getTaskId() {
    return taskId;
  }
  public void setTaskId(Integer taskId) {
    this.taskId = taskId;
  }
  public String getTaskName() {
    return taskName;
  }
  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }
  public Integer getTaskType() {
    return taskType;
  }
  public void setTaskType(Integer taskType) {
    this.taskType = taskType;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getParallel() {
    return parallel;
  }
  public void setParallel(int parallel) {
    this.parallel = parallel;
  }
  public int getUsed() {
    return used;
  }
  public void setUsed(int used) {
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
