package com.xiaoshabao.webframework.ui.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.webframework.ui.component.FormConstants;
import com.xiaoshabao.webframework.ui.component.FormEngineComponet;
import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.FormValidateInfo;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.service.element.UIElement;

public abstract class AbstractTemplateServiceImpl extends AbstractFromServiceImpl {

	@Resource(name = "formEngineComponet")
	protected FormEngineComponet formEngineComponet;

	/**
	 * 获得所有 模版数据
	 * @param template
	 * @param data
	 * @param isLoadWhere 是否默认加载where条件
	 * @return
	 */
	public final TemplateData getTemplateElements(TemplateEntity template,
			Map<String, Object> data,boolean isLoadWhere) {
		logger.debug("开始处理模版{}", template.getTemplateId());
		TemplateData result = new TemplateData(false);
		List<ElementColumnDto> columnList = getTemplateElementData(template);

		if (columnList == null) {
			logger.info("模版{} 未获得任何元素", template.getTemplateId());
			result.setMessage("模版没有任何内容");
			return result;
		}
		StringBuffer rs = new StringBuffer();
		StringBuffer whereSQL = new StringBuffer();
		
		for (ElementColumnDto elementDto : columnList) {
			logger.debug("处理模版元素{}", elementDto.getElementId());
			// 预处理元素参数
			Map<String, Object> elementParams = getElementParams(
					elementDto.getElementId(), elementDto.getElementParams(),
					elementDto.getElement().getParams());

			setPublicProperties(data,elementParams, elementDto);

			// 是否只读模版
			boolean isReadOnly = elementDto.getIsReadOnly();

			// 设置header
			getElementHeader(result, elementParams, isReadOnly);

			String elementServiceType = formEngineComponet
					.getElementSerivceType(elementDto.getElement()
							.getElementType());
			
			UIElement element = ApplicationContextUtil.getBean(
					elementServiceType, UIElement.class);
			
			//设置元素自定义值
			String fieldCode=elementDto.getTableColumn().getFieldCode();
			Object value=element.getCustomValue(data, elementParams, fieldCode, data.get(fieldCode));
			data.put(fieldCode, value);
			elementParams.put(FormConstants.ELEMENT_VALUE, value);
			
			// 获得元素自定义值等
			element.getCustomParams(elementDto, data, elementParams);

			elementParams.putAll(data);
			rs.append(element.render(elementDto.getElement(), elementParams,
					isReadOnly));
			if(isLoadWhere){
			  appendWhereSql(whereSQL,elementDto.getTableColumn().getFieldCode(),data);
			}
			logger.debug("模版元素{}处理完成", elementDto.getElementId());
		}
		result.setSuccess(true);
		result.setContentHtml(rs.toString());
		if(isLoadWhere){
		  result.setWhereSql(whereSQL.toString());;
    }
		return result;
	}
	
	protected void appendWhereSql(StringBuffer whereSQL,String fieldCode,Map<String,Object> data){
	  String value=data.get(fieldCode).toString();
	  if(StringUtils.isEmpty(value)){
	    return;
	  }
	  whereSQL.append(" AND ");
	  whereSQL.append(fieldCode);
	  whereSQL.append("=#{");
	  whereSQL.append(value);
	  whereSQL.append("}# ");
	}

	/**
	 * 获得预算数据列数据<br>
	 * 可以有不同的实现，缓存和直接查询等
	 * 
	 * @param template
	 * @return
	 */
	protected List<ElementColumnDto> getTemplateElementData(
			TemplateEntity template) {
		return this.baseDao.getData(ElementColumnDto.class, template);
	}

	/**
	 * 验证方法入口
	 * <p>
	 * 包括各种情况
	 * </p>
	 * 
	 * @param result
	 * @param element
	 * @param params
	 * @return
	 */
	protected final FormValidateInfo validatePublicParams(
			ElementColumnDto elementDto, UIElement element,
			Map<String, Object> data, Map<String, Object> elementParams) {
		FormValidateInfo result = null;
		// 公共参数验证，大小等
		result = this.validatePublicParams(elementDto, data);
		if (!result.isSuccess()) {
			return result;
		}
		// 验证uielement的特殊方法
		result = element.validateData(data, elementDto, elementParams);
		if (!result.isSuccess()) {
			return result;
		}
		return validateParams(elementDto, data);
	}

	/**
	 * 验证公共参数
	 */
	private FormValidateInfo validatePublicParams(ElementColumnDto elementDto,
			Map<String, Object> data) {
		FormValidateInfo info = new FormValidateInfo(false);
		// 公共参数验证，大小等

		info.setSuccess(true);
		return info;
	}

	/**
	 * 验证方法（模版特殊验证方法）
	 * <p>
	 * 默认无实现
	 * </p>
	 * 
	 * @param result
	 * @param element
	 * @param params
	 * @return
	 */
	protected FormValidateInfo validateParams(ElementColumnDto elementDto,
			Map<String, Object> data) {
		return new FormValidateInfo(true);
	}

	/**
	 * 获得元素参数
	 * 
	 * @param elementId
	 * @param columnParams
	 * @param elementParams
	 * @return
	 */
	protected Map<String, Object> getElementParams(String elementId,
			String columnParams, String elementParams) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(elementParams)) {
			JSONObject elementJson = JSONObject.parseObject(elementParams);
			if (!elementJson.isEmpty()) {
				result = elementJson;
			}
		}
		if (StringUtils.isNotEmpty(columnParams)) {
			JSONObject columnJson = JSONObject.parseObject(columnParams);
			if (!columnJson.isEmpty()) {
				result.putAll(columnJson);
			}
		}

		return result;
	}

	/**
	 * 设置公共参数
	 * 
	 * @param params
	 *            参数
	 * @param column
	 * @return
	 */
	protected Map<String, Object> setPublicProperties(Map<String, Object> data,
			Map<String, Object> params, ElementColumnDto elementDto) {
	  
	  String fieldCode=elementDto.getTableColumn().getFieldCode();
	  
		params.put(FormConstants.ELEMENT_TEMPLATE_ID, elementDto.getTemplateId());
		params.put(FormConstants.ELEMENT_ELEMENT_ID, elementDto.getElementId());
		params.put(FormConstants.ELEMENT_FIELD_CODE, fieldCode);
		params.put(FormConstants.ELEMENT_LABEL, elementDto.getLabel());
		
		//设置值
		Object value=data.get(fieldCode);
		if(value==null){
		  value=elementDto.getDefaultValue();
		  data.put(fieldCode, value);//反写到数据
		}
		params.put(FormConstants.ELEMENT_VALUE, value);

		return params;
	}

	/**
	 * 获得header引用参数
	 * 
	 * @param templateData
	 *            模版返回结果
	 * @param params
	 *            元素JSON参数
	 */
	private void getElementHeader(TemplateData templateData,
			Map<String, Object> params, boolean isReadOnly) {
		Object obj = null;
		if (isReadOnly) {
			obj = params.get(FormConstants.HEADER_STR_READ);
		} else {
			obj = params.get(FormConstants.HEADER_STR_VIEW);
		}

		if (obj == null) {
			obj = params.get(FormConstants.HEADER_STR_ALL);
		}

		// 转换成set
		if (obj != null && obj instanceof JSONArray) {
			JSONArray headers = (JSONArray) obj;
			if (headers.size() < 1) {
				return;
			}
			Set<String> header = templateData.getHeader();
			for (int i = 0; i < headers.size(); i++) {
				header.add(headers.get(0).toString());
			}
		}
	}
	
	
  public String getQueryWhereSQL(List<ElementColumnDto> elementList, Map<String, Object> data) {
    StringBuilder sql=new StringBuilder();
    FormValidateInfo validate;
    for(ElementColumnDto elementDto:elementList){
     /* validate=this.validatePublicParams(elementDto, element, data, elementParams);
      if(!validate.isSuccess()){
        
      }*/
    }
    return null;
  }

}
