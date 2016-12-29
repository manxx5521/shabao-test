package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
/**
 * 表单服务
 */
public interface FormService {
	/**
	 * 获得模版数据
	 * @param templateId 模版id
	 * @return
	 */
	public TemplateData getTemplateData(String templateId);
	
	/**
	 *  相应元素web请求
	 * @param engineType 引擎类型
	 * @param elementId 元素id
	 * @param params 表单参数
	 * @return
	 */
	public AjaxResult getElementResponse(String engineType,String elementId,Map<String, Object> params);
}
