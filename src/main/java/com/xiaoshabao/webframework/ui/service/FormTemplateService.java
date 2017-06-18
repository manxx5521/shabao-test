package com.xiaoshabao.webframework.ui.service;

import java.util.List;
import java.util.Map;

import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.FormField;
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
	public TemplateData getTemplate(TemplateEntity template,Map<String, Object> data);
	
	/**
	 * 获查询条件模版
	 * @return
	 */
  public String getTemplateQuerySQL(String tableName,String templateId,Map<String, Object> data);
  
  /**
   * 获得模版字段信息
   * @param billDto
   * @return
   */
  public List<FormField> getTemplateField(String templateId,Map<String, Object> data);

}
