package com.xiaoshabao.webframework.ui.dao;

import java.util.List;

import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;

/**
 * 操作元素表的DAO
 */
public interface ElementDao {
	/**
	 * 获得模版实体根据id
	 * @param tmplateId 模版id
	 * @return
	 */
	public TemplateEntity getTemplateByid(String templateId);
	/**
	 * 根据模版id获取元素，列表
	 * @param templateId 模版id
	 * @return
	 */
	public List<TemplateElementEntity> getTemplateElements(String templateId);
	/**
	 * 根据id获取 元素
	 * @param elementId
	 */
	public ElementEntity getElementById(String elementId);

}
