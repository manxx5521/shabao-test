package com.xiaoshabao.webframework.ui.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.service.TemplateFactory;
import com.xiaoshabao.webframework.ui.service.element.WebElement;

public abstract class AbstractTemplateFactoryImpl extends AbstractTemplateServiceImpl3 implements TemplateFactory {
	
	//默认的表单AJAX响应
	@Override
	public AjaxResult getElementResponse(ElementEntity element,
			Map<String, Object> params) {
		String elementType = formEngineComponet.getElementType(element.getElementType());
		if (StringUtils.isEmpty(elementType)) {
			logger.error("未找到元素类型对应的 元素，请查看元素类型服务映射关系；元素{}。", element.getElementId());
			return new AjaxResult("元素类型错误");
		}

		WebElement webelement = ApplicationContextUtil.getBean(elementType,WebElement.class);
		// 初始化元素数据
		JSONObject paramJSON=webelement.initData(params,element);
		webelement.setSessionParams(params, element);
		return webelement.getElementResponse(params,paramJSON);
	}
  
}
