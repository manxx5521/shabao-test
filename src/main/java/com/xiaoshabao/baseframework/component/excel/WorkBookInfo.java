package com.xiaoshabao.baseframework.component.excel;

import java.util.List;

public class WorkBookInfo {
  private String name;

  private List<SheetInfo> sheetList;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<SheetInfo> getSheetList() {
    return sheetList;
  }

  public void setSheetList(List<SheetInfo> sheetList) {
    this.sheetList = sheetList;
  }

}
