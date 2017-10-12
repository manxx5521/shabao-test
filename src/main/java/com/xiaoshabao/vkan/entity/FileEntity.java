package com.xiaoshabao.vkan.entity;

public class FileEntity {
  private String fileId;
  private String fileName;
  private String path;
  private String md5;
  private String parentId;
  
  public String getFileId() {
    return fileId;
  }
  public void setFileId(String fileId) {
    this.fileId = fileId;
  }
  public String getFileName() {
    return fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public String getMd5() {
    return md5;
  }
  public void setMd5(String md5) {
    this.md5 = md5;
  }
  public String getParentId() {
    return parentId;
  }
  public void setParentId(String parentId) {
    this.parentId = parentId;
  }
  
}
