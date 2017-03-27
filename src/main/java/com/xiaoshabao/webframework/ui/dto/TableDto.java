package com.xiaoshabao.webframework.ui.dto;

import java.util.List;

import com.xiaoshabao.webframework.ui.entity.TableElement;
import com.xiaoshabao.webframework.ui.entity.TableEntity;

public class TableDto extends TableEntity{
  
  private List<TableElement> tableElements;

  public List<TableElement> getTableElements() {
    return tableElements;
  }

  public void setTableElements(List<TableElement> tableElements) {
    this.tableElements = tableElements;
  }
  
}
