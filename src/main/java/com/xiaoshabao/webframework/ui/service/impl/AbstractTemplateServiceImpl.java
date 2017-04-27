package com.xiaoshabao.webframework.ui.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.webframework.ui.component.FormConstants;
import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
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
	public final TemplateData getTemplateElements(TemplateEntity template,Map<String, Object> params){
		
		List<ElementColumnDto> columnList = getTemplateElementData(template);
		
		if(columnList==null){
			logger.info("模版{} 未获得任何元素", template.getTemplateId());
			return new TemplateData("模版没有任何内容");
		}
		StringBuffer rs = new StringBuffer();
		for (ElementColumnDto column : columnList) {
			UIElement element = ApplicationContextUtil.getBean(column.getElement().getElementType(),UIElement.class);
			
			//验证 返回的信息NULL时为正常
			String message=null;
			message=validateElement(message,column.getElement(),params);
			if(message!=null){
				return new TemplateData(message);
			}
			Map<String,Object> elementParams=this.getElementParams(column.getElementId(), column.getElementsParams(), column.getElement().getParams());
			
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
	/**
	 * 获得预算数据列数据<br>
	 * 可以有不同的实现，缓存和直接查询等
	 * @param template
	 * @return
	 */
	protected List<ElementColumnDto> getTemplateElementData(TemplateEntity template){
		return this.baseDao.getData(ElementColumnDto.class, template);
	}
	
	/**
	 * 验证方法（模版特殊验证方法）
	 * @param result
	 * @param element
	 * @param params
	 * @return
	 */
	protected String validateElement(String result,ElementEntity element,Map<String, Object> data){
		return result;
	}
	
	/**
	 * 获得元素参数
	 * @param elementId
	 * @param columnParams
	 * @param elementParams
	 * @return
	 */
	protected Map<String,Object> getElementParams(String elementId,String columnParams,String elementParams){
		Map<String,Object> result=new HashMap<String,Object>();
		if(elementParams!=null){
			JSONObject elementJson=JSONObject.parseObject(columnParams);
			if(!elementJson.isEmpty()){
				result=elementJson;
			}
		}
		if(columnParams!=null){
			JSONObject columnJson=JSONObject.parseObject(columnParams);
			if(!columnJson.isEmpty()){
				result.putAll(columnJson);
			}
		}
		
		return result;
	}
	
	/**
	 * 设置公共参数
	 * @param params 参数
	 * @param column 
	 * @return
	 */
	protected Map<String,Object> setPublicProperties(Map<String,Object> params,ElementColumnDto column){
		params.put(FormConstants.ELEMENT_FIELD_CODE, column.getColumn().getFieldCode());
		params.put(FormConstants.ELEMENT_LABEL, column.getLabel());
		return params;
	}

}
