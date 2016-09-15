package com.xiaoshabao.webframework.ui.dao;

import java.util.List;

import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.webframework.ui.entity.FormElement;
import com.xiaoshabao.webframework.ui.entity.FormElementDef;

/**
 * 获取组件信息
 */
public interface UIElementDao extends BaseDao {

	public FormElementDef getElementDef(Long elementid);

	public FormElement getElement(String templateid, Long elementid);

	public List<FormElement> getElements(String templateid);
	
}
