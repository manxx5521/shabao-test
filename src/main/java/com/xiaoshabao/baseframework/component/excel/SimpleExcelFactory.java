package com.xiaoshabao.baseframework.component.excel;

import java.util.List;
import java.util.Map;

/**
 * 简单的excel工厂（单行标题）
 */
public class SimpleExcelFactory extends AbstractExcelFactory {
  /** 设置标题 **/
  private List<ColumnEntity> columns;

  /** 设置数据 **/
  private List<Map<String, Object>> datas;
  
  

  public SimpleExcelFactory() {
    super();
  }

  public SimpleExcelFactory(List<ColumnEntity> titles, List<Map<String, Object>> datas) {
    super();
    this.columns = titles;
    this.datas = datas;
  }

  @Override
  protected void exportExcel() {
    WorkBookInfo workBookInfo=WorkBookUtil.createWorkBookSimple(fileName, columns, datas);
    this.bookInfo=workBookInfo;
  }
  
}
