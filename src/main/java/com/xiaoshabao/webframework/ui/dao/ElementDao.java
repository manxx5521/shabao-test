package com.xiaoshabao.webframework.ui.dao;

import java.util.List;

import com.xiaoshabao.webframework.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.FormElementDef;
import com.xiaoshabao.webframework.ui.entity.TemplatElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;

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
	
	/**********new**************************/
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
	public List<TemplatElementEntity> getTemplateElements(String templateId);
	/**
	 * 根据id获取 元素
	 * @param elementId
	 */
	public com.xiaoshabao.webframework.ui.entity.ElementEntity getElementById1(String elementId);

}
