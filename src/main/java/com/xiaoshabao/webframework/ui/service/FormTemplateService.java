package com.xiaoshabao.webframework.ui.service;

import java.util.List;
import java.util.Map;

import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;

/**
 * 表单模版服务
 */
public interface FormTemplateService {
	/**
	 * 获取模版数据
	 * @param template
	 * @param isLoadWhere 是否加载where条件
	 * @return
	 */
	public TemplateData getTemplate(TemplateEntity template,Map<String, Object> data,boolean isLoadWhere);
	
	/**
	 * 获取模版查询sql
	 * @param elementList
	 * @param data
	 * @return
	 */
  public String getTemplateQuerySQL(List<ElementColumnDto> elementList,Map<String, Object> data);

}
