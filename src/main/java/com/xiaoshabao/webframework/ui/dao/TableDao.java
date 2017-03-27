package com.xiaoshabao.webframework.ui.dao;

import java.util.List;

import com.xiaoshabao.webframework.ui.entity.TableElement;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
/**
 * 数据源相关
 */
public interface TableDao {
  /**
   * 添加td_ui_table的信息
   * @param table
   * @return
   */
  public int addTableDesc(TableEntity table);
  /**
   * 添加td_ui_table_element 元素的信息
   * @param table
   * @return
   */
  public int addTableElements(List<TableElement> elements);

}
