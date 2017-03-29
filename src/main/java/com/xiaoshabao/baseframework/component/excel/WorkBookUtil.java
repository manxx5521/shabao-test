package com.xiaoshabao.baseframework.component.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkBookUtil {
  
  /**
   * 创建一个简单的WorkBook
   */
  public static WorkBookInfo createWorkBookSimple(String fileName,List<ColumnEntity> columns,List<Map<String, Object>> datas){
    WorkBookInfo workBook=new WorkBookInfo();
    SheetInfo sheetInfo=new SheetInfo();
    sheetInfo.setDataset(datas);
    sheetInfo.setColumns(columns);
    List<SheetInfo> sheetList=new ArrayList<SheetInfo>();
    sheetList.add(sheetInfo);
    workBook.setSheetList(sheetList);
    workBook.setName(fileName);
    return workBook;
  }

}
