package com.xiaoshabao.webframework.ui.dao;

import com.xiaoshabao.webframework.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.FormElementDef;

/**
 * 操作元素表的DAO
 */
public interface ElementDao {
	/**
	 * 根据id获取 元素
	 * @param elementId
	 */
	public ElementEntity getElementById(Integer elementId);
	/**
	 * 获得element表信息
	 * @param elementId
	 * @return
	 */
	public FormElementDef getElementDef(Long elementId);

}
