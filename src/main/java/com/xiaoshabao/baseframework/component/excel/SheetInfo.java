package com.xiaoshabao.baseframework.component.excel;

import java.util.List;

public class SheetInfo {
  
  private String sheetName;
  
  private List<?> dataset;
  private List<ColumnEntity> columns;

  public String getSheetName() {
    return sheetName;
  }

  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }

  public List<?> getDataset() {
    return dataset;
  }

  public void setDataset(List<?> dataset) {
    this.dataset = dataset;
  }

  public List<ColumnEntity> getColumns() {
    return columns;
  }

  public void setColumns(List<ColumnEntity> columns) {
    this.columns = columns;
  }
}
