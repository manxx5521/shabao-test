package com.xiaoshabao.baseframework.component.excel;

import java.util.List;

public class ColumnEntity extends ColumnBase{
  
  
  private List<ColumnEntity> node;

  public List<ColumnEntity> getNode() {
    return node;
  }

  public void setNode(List<ColumnEntity> node) {
    this.node = node;
  }
}
