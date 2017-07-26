package com.xiaoshabao.upgrade.entity;

import java.io.Serializable;
/**
 * 文件描述
 */
public class UpgradeFileDetail implements Serializable{
  
  private static final long serialVersionUID = 1L;

  private String name;

  private String path;

  private String date;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

}
