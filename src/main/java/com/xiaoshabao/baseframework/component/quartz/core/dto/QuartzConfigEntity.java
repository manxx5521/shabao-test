package com.xiaoshabao.baseframework.component.quartz.core.dto;

/**
 * 配置
 */
public class QuartzConfigEntity {

  private String configId;

  private String configValue;

  private String remark;

  private Integer used;

  public String getConfigId() {
    return configId;
  }

  public void setConfigId(String configId) {
    this.configId = configId;
  }

  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getUsed() {
    return used;
  }

  public void setUsed(Integer used) {
    this.used = used;
  }

}
