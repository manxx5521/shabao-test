package com.xiaoshabao.webframework.ui.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.service.element.UIElement;

public abstract class AbstractTemplateServiceImpl{
	
	protected Logger logger;

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;

	public AbstractTemplateServiceImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}
	
	/**
	 * 获得所有 ElementDto 集合
	 * @return
	 */
	public TemplateData getTemplateElements(TemplateEntity template,Map<String, Object> params){
		List<ElementColumnDto> columnList = this.baseDao.getData(ElementColumnDto.class, template);
		if(columnList==null){
			logger.info("模版{} 未获得任何元素", template.getTemplateId());
			return new TemplateData();
		}
		StringBuffer rs = new StringBuffer();
		for (ElementColumnDto column : columnList) {
			UIElement uielement = ApplicationContextUtil.getBean(column.getElement().getElementType(),UIElement.class);
			// 初始化元素数据
			/*JSONObject paramJSON=uielement.initData(params,te, element);
			uielement.setPublicProperties(params,te, element);
			uielement.setCustomParams(params,paramJSON);
			rs.append(uielement.render(params, element));*/
			/*ElementEntity element = this.elementDao.getElementById(te.getElementId());
			String elementType = this.formEngineComponet.getElementType(element.getElementType());
			if (elementType == null) {
				logger.error("模版{} 未获得元素{}的类型", templateId,element.getElementId());
				continue;
			}
			UIElement uielement = ApplicationContextUtil.getBean(elementType,UIElement.class);
			// 初始化元素数据
			JSONObject paramJSON=uielement.initData(params,te, element);
			uielement.setPublicProperties(params,te, element);
			uielement.setCustomParams(params,paramJSON);
			rs.append(uielement.render(params, element));*/
		}
		return null;
	}

}
