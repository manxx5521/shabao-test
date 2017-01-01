package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;

/**
 * 表单工厂
 */
public interface TemplateFactory {
	/**
	 * 获得表单元素
	 * @param templateId 模版id，必传
	 */
	public String getTemplateElements(String templateId,Map<String, Object> params);

	/**
	 * 相应元素web请求
	 * @param element 元素实体
	 * @param params 表单参数
	 * @return
	 */
	public AjaxResult getElementResponse(ElementEntity element,Map<String, Object> params);
}
