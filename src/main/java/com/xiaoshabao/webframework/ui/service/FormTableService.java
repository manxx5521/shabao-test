package com.xiaoshabao.webframework.ui.service;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.TableDto;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
/**
 * 表单服务
 */
public interface FormTableService{
	
	/**
	 * 获得表信息
	 * <p>此方法进行缓存，里边做非空验证，返回正确的结果</p>
	 * @param tableId
	 * @return 返回正确的结果，否则异常
	 */
	public TableEntity getTable(String tableId);	
	
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
