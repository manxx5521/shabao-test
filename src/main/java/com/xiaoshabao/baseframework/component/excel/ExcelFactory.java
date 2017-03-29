package com.xiaoshabao.baseframework.component.excel;

import java.io.IOException;
import java.io.OutputStream;

/**
 * ExcelFactory
 */
public interface ExcelFactory{
  
  /**
   * 解析到文件路径
   */
  public boolean exportExcelForPath(String FileName, String path) throws IOException;
  
  /**
   * 解析到文件路径
   */
  public boolean exportExcelForPath(String path) throws IOException;
  
  /**
   * 解析到输出流
   */
  public OutputStream exportExcelForOutputStream() throws IOException;

}
