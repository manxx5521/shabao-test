package com.xiaoshabao.webframework.ui.service;

import java.util.List;

import com.xiaoshabao.webframework.ui.entity.TableColumnEntity;

/**
 * 数据表列Service
 */
public interface TableColumnService {
	/**
	 * 根据id获得数据列集合
	 * <p>此方法会做缓存，并且做非空验证，返回为不为0的列表</p>
	 * @param tableId
	 * @return
	 */
	public List<TableColumnEntity> getTableColumn(String tableId);
	
	
}
