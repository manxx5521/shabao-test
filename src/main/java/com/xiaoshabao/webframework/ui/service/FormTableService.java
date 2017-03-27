package com.xiaoshabao.webframework.ui.service;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.TableDto;
/**
 * 表单服务
 */
public interface FormTableService{
  /**
   * 获得表单详情
   */
  public AjaxResult getTableInfo(String tableId);
  /**
   * 添加数据源
   * @param table
   * @return
   */
  public AjaxResult addTable(TableDto table);
}
