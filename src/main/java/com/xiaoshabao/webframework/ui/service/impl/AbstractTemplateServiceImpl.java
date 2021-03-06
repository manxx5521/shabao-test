package com.xiaoshabao.webframework.ui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.ui.component.FormConstants;
import com.xiaoshabao.webframework.ui.component.FormEngineComponet;
import com.xiaoshabao.webframework.ui.dto.ElementColumnDto;
import com.xiaoshabao.webframework.ui.dto.FormValidateInfo;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.FormField;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.enums.ViewTypeEnum;
import com.xiaoshabao.webframework.ui.service.element.UIElement;
/*
 * 模版要修改，修改后，只取数据表默认值
 */
public abstract class AbstractTemplateServiceImpl extends
		AbstractFormServiceImpl {

	@Resource(name = "formEngineComponet")
	protected FormEngineComponet formEngineComponet;

	/**
	 * 获得所有 模版数据
	 * 
	 * @param template
	 * @param data
	 * @param isLoadWhere
	 *            是否默认加载where条件
	 * @return
	 */
	public final TemplateData getTemplateElements(TemplateEntity template,
			Map<String, Object> data) {
		logger.debug("开始处理模版{}", template.getTemplateId());
		TemplateData result = new TemplateData(false);
		List<ElementColumnDto> columnList = getTemplateElementData(template.getTemplateId());
		
		//判断是否带入id，带入id取表数据
		Object objID=data.get(FormConstants.REQ_ID_TAG);
		if(objID!=null&&objID instanceof String){
			String reqID=objID.toString();
			//如果有id
			if(StringUtils.isNotEmpty(reqID)){
				String tableId=template.getTableId();
				TableEntity tableEntity=this.baseDao.getDataById(TableEntity.class, tableId);
				String selectSql=this.getTemplateQuerySQL(tableEntity.getTableName(), columnList, template.getTemplateId(), data, false, true);
				Map<String,Object> selectData=this.baseDao.getSqlMapper().selectOne(selectSql, data);
				data.putAll(selectData);
			}
		}

		if (columnList == null) {
			logger.info("模版{} 未获得任何元素", template.getTemplateId());
			result.setMessage("模版没有任何内容");
			return result;
		}
		StringBuffer rs = new StringBuffer();
		//页面属性，是否只读
		boolean viewReadOnly=false;
		Object viewTypeObj=data.get(FormConstants.REQ_VIEW_TYPE_ENUM);
		if(viewTypeObj!=null&&viewTypeObj instanceof ViewTypeEnum){
			viewReadOnly=((ViewTypeEnum)viewTypeObj).isReadOnly();
		}
		for (ElementColumnDto elementDto : columnList) {
			logger.debug("处理模版元素{}", elementDto.getElementId());

			String elementServiceType = formEngineComponet
					.getElementSerivceType(elementDto.getElement()
							.getElementType());

			UIElement element = ApplicationContextUtil.getBean(
					elementServiceType, UIElement.class);

			// 预处理元素参数
			Map<String, Object> elementParams = element.getElementParams(
					elementDto.getElement().getParams(),
					elementDto.getExtParams());

			setPublicProperties(data, elementParams, elementDto);

			// 是否只读模版
			boolean isReadOnly = elementDto.getIsReadOnly();
			//如果能修改，还要看页面处于位置，比如展示界面不可修改为只读
			isReadOnly=isReadOnly?isReadOnly:viewReadOnly;

			// 设置header
			getElementHeader(result, elementParams, isReadOnly);

			// 设置元素自定义值
			String fieldCode = elementDto.getTableColumn().getFieldCode();
			Object value = element.getCustomValue(data, elementParams,
					fieldCode, data.get(fieldCode));
			data.put(fieldCode, value);
			elementParams.put(FormConstants.ELEMENT_VALUE, value);

			// 获得元素自定义值等
			element.getCustomParams(elementDto, data, elementParams);

			elementParams.putAll(data);
			rs.append(element.render(elementDto.getElement(), elementParams,
					isReadOnly));
			logger.debug("模版元素{}处理完成", elementDto.getElementId());
		}
		result.setSuccess(true);
		result.setContentHtml(rs.toString());
		return result;
	}


	/**
	 * 获得预算数据列数据<br>
	 * 可以有不同的实现，缓存和直接查询等
	 * 
	 * @param template
	 * @return
	 */
	protected List<ElementColumnDto> getTemplateElementData(
	  String templateId) {
		return this.baseDao.getData(ElementColumnDto.class, templateId);
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
	protected final FormValidateInfo validateDataAll(
			ElementColumnDto elementDto, UIElement element,
			Map<String, Object> data, Map<String, Object> elementParams) {
		FormValidateInfo result = null;
		// 验证uielement的特殊方法
		result = element.validateData(data, elementDto, elementParams);
		if (!result.isSuccess()) {
			return result;
		}
		return validateData(elementDto, data);
	}

	/**
	 * 验证方法（模版特殊验证方法）
	 * <p>
	 * 默认无实现
	 * </p>
	 */
	protected FormValidateInfo validateData(ElementColumnDto elementDto,
			Map<String, Object> data) {
		return new FormValidateInfo(true);
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

		String fieldCode = elementDto.getTableColumn().getFieldCode();

		params.put(FormConstants.ELEMENT_TEMPLATE_ID,
				elementDto.getTemplateId());
		params.put(FormConstants.ELEMENT_ELEMENT_ID, elementDto.getElementId());
		params.put(FormConstants.ELEMENT_FIELD_CODE, fieldCode);
		params.put(FormConstants.ELEMENT_LABEL, elementDto.getLabel());

		// 设置值
		Object value = data.get(fieldCode);
		if (value == null) {
			value = elementDto.getDefaultValue();
			data.put(fieldCode, value);// 反写到数据
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

	/**
	 * 获得查询sql
	 * @param templateId
	 * @return
	 */
	public String getTemplateQuerySQL(String tableName,String templateId,Map<String, Object> data) {
	  List<ElementColumnDto> columnList = getTemplateElementData(templateId);
	  return getTemplateQuerySQL(tableName,columnList,templateId,data,true,false);
	}
	
	/**
	 * 获得查询sql
	 * @param tableName 数据表名
	 * @param columnList 列列表
	 * @param templateId 模版
	 * @param data 数据
	 * @param vaildateTag 是否验证条件的正确性
	 * @param idTag id标识，为true时直接在where条件添加id条件
	 * @return
	 */
	public String getTemplateQuerySQL(String tableName,List<ElementColumnDto> columnList,String templateId,Map<String, Object> data,
			boolean vaildateTag,boolean idTag) {
	  logger.debug("start-→开始组装“条件区”查询数据sql。模版{}",templateId);
	  StringBuffer sql = new StringBuffer();
	  String idColumn=null;
	  
	  if (columnList == null) {
		  logger.info("查询数据时，条件区模版{} 未获得任何元素", templateId);
		  return "1=1 ";
	  }
    
    for (ElementColumnDto elementDto : columnList) {
      logger.debug("处理模版元素{}:{}", elementDto.getElementId(),elementDto.getLabel());

      String elementServiceType = formEngineComponet
          .getElementSerivceType(elementDto.getElement()
              .getElementType());

      UIElement element = ApplicationContextUtil.getBean(
          elementServiceType, UIElement.class);

      // 预处理元素参数
      Map<String, Object> elementParams = element.getElementParams(
          elementDto.getElement().getParams(),
          elementDto.getExtParams());

      //验证参数
      if(vaildateTag){
    	  FormValidateInfo validateInfo=validateDataAll(elementDto, element, data, elementParams);
          if(!validateInfo.isSuccess()){
            throw new MsgErrorException(String.format("%s %s",elementDto.getLabel(),
                validateInfo.getMessage()==null?"未能验证通过":validateInfo.getMessage()));
          } 
      }
     
      //确定主键字段
      if(idTag){
    	  if(elementDto.getTableColumn().isKey()){
    		  idColumn=elementDto.getTableColumn().getFieldCode();
    	  }
    	  addSqlBySelect(tableName,sql,elementDto.getTableColumn().getFieldCode());
      }else{
    	//添加sql
          addSqlBySqlMapper(tableName,sql,elementDto.getTableColumn().getFieldCode());
      }
      
      logger.debug("模版元素{}处理完成", elementDto.getElementId());
    }
    if(idColumn!=null){
    	StringBuilder selectSql=new StringBuilder();
    	selectSql.append("SELECT 1");
    	selectSql.append(sql);
    	selectSql.append(" FROM ");
    	selectSql.append(tableName);
    	selectSql.append(" WHERE ");
    	selectSql.append(idColumn);
    	selectSql.append("=");
    	selectSql.append("#{");
    	selectSql.append(FormConstants.REQ_ID_TAG);
    	selectSql.append("}");
    	return selectSql.toString();
    }
    logger.debug("end-→结束组装“条件区”查询数据sql。模版{}",templateId);
    return "1=1 "+sql.toString();
	}
	
	/**
	 * 添加脚本自定义sql
	 * @param sql
	 * @param column
	 */
	protected void addSqlBySqlMapper(final String tableName,StringBuffer sql,String column){
//	    String sql1=  "<if test=\"usertype != null\">usertype = #{usertype}</if>";
	  sql.append("\n <if test=\"");
	  sql.append(column);
	  sql.append(" !=null and ");
	  sql.append(column);
	  sql.append(" !='' \"> and ");
	  sql.append(tableName);
	  sql.append(".");
	  sql.append(column);
	  sql.append("=#{");
	  sql.append(column);
	  sql.append("} </if> ");
	}
	
	/**
	 * 添加查询sql
	 * @param sql
	 * @param column
	 */
	protected void addSqlBySelect(final String tableName, StringBuffer sql,String column) {
		sql.append(",");
		sql.append(tableName);
		sql.append(".");
		sql.append(column);
	}
	
	/**
	 * 获得模版字段信息
	 * @param templateId
	 * @return
	 */
	public List<FormField> getTemplateField(String templateId,Map<String, Object> data) {
		logger.debug("start-→组装模版字段信息。模版{}",templateId);
		  
		List<ElementColumnDto> columnList = getTemplateElementData(templateId);
		  
	    if (columnList == null) {
	      logger.info("查询数据时，模版{} 未获得任何元素", templateId);
	      throw new ServiceException("未获得任何字段信息");
	    }
	    List<FormField> result=new ArrayList<FormField>(columnList.size());
	    for (ElementColumnDto elementDto : columnList) {
	      logger.debug("处理模版元素{}:{}", elementDto.getElementId(),elementDto.getLabel());

	      String elementServiceType = formEngineComponet
	          .getElementSerivceType(elementDto.getElement()
	              .getElementType());

	      UIElement element = ApplicationContextUtil.getBean(
	          elementServiceType, UIElement.class);

	      // 预处理元素参数
	      Map<String, Object> elementParams = element.getElementParams(
	          elementDto.getElement().getParams(),
	          elementDto.getExtParams());

	      //验证参数
	      FormValidateInfo validateInfo=validateDataAll(elementDto, element, data, elementParams);
	      if(!validateInfo.isSuccess()){
	        throw new MsgErrorException(String.format("%s %s",elementDto.getLabel(),
	            validateInfo.getMessage()==null?"未能验证通过":validateInfo.getMessage()));
	      }
	      
	      result.add(elementDto.getTableColumn());
	      logger.debug("模版元素{}处理完成", elementDto.getElementId());
	    }
	    
	    logger.debug("end-→组装模版字段信息。模版{}",templateId);
	    return result;
	}

}
