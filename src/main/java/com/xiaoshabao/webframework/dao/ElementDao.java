package com.xiaoshabao.webframework.dao;

import com.xiaoshabao.webframework.entity.ElementEntity;

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
