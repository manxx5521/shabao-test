package com.xiaoshabao.baseframework.component.quartz.core.dto;

public enum QuartzStatus {
  STATUS_0("0","无任务"),
  STATUS_N("N","任务未装载"),
  STATUS_Y("Y","已经装载任务，等待触发"),
  STATUS_L("L","任务已过期"),
  STATUS_1("1","正在执行"),
  STATUS_2("2","完成"),
  STATUS_40("40","程序错误"),
  STATUS_41("41","执行失败，未找到相关任务");
  
  private String code;
  
  
  private String message;
  
  private QuartzStatus(String code,String message){
    this.code=code;
    this.message=message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

}
