package com.xiaoshabao.webframework.ui.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.component.FormEngineComponet;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.service.TemplateFactory;
import com.xiaoshabao.webframework.ui.service.element.WebElement;

public abstract class AbstractTemplateFactoryImpl extends AbstractTemplateServiceImpl implements TemplateFactory {
	
	@Resource(name="formEngineComponet")
	protected FormEngineComponet formEngineComponet;
	
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
		webelement.initData(element);
		webelement.setBeanParams(params);
		return webelement.getElementResponse(params);
	}
  
}
