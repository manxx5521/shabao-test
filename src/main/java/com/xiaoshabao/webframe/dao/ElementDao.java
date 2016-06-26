package com.xiaoshabao.webframe.dao;

import com.xiaoshabao.webframe.entity.ElementEntity;

/**
 * 操作元素表的DAO
 */
public interface ElementDao {
	/**
	 * 根据id获取 元素
	 * @param elementId
	 */
	public ElementEntity getElementById(Integer elementId);

}
