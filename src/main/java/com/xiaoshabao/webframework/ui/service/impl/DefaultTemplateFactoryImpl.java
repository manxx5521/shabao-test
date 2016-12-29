package com.xiaoshabao.webframework.ui.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplatElementEntity;
import com.xiaoshabao.webframework.ui.service.element.UIElement;

@Service("defaultTemplateFactoryImpl")
public class DefaultTemplateFactoryImpl extends AbstractTemplateFactoryImpl {

	@Override
	public String getTemplateElements(String templateId) {
		List<TemplatElementEntity> elementList = this.elementDao
				.getTemplateElements(templateId);
		StringBuffer rs = new StringBuffer();
		for (TemplatElementEntity te : elementList) {
			ElementEntity element = this.elementDao.getElementById1(te.getElementId());
			String elementType = this.formEngineComponet.getElementType(element.getElementType());
			if (elementType == null) {
				logger.error("模版{} 未获得元素{}的类型", templateId,element.getElementId());
				continue;
			}
			UIElement uielement = ApplicationContextUtil.getBean(elementType,UIElement.class);
			// 初始化元素数据
			uielement.initData(te, element);
			Map<String, Object> params = new HashMap<String, Object>();
			uielement.setPublicProperties(params);
			uielement.setCustomParams(params);
			rs.append(uielement.render(params));
			uielement.clear();
		}
		return rs.toString();
	}

}
