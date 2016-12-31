package com.xiaoshabao.webframework.ui.component;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xiaoshabao.webframework.component.SessionParams;

/**
 * 表单引擎组件(XML注入)
 */
public class FormEngineComponet {
	/** 引擎类型 **/
	private Map<String,String> engineType;
	/** 元素类型 **/
	private Map<String,String> elementType;
	/** freemarker模版类型 **/
	private Map<String,String> templateType;
	/** 默认freemarker模版类型 **/
	private String defaultTemplateType="viewTemplate";
	/**获得子项目session**/
	private SessionParams sessionComponet;
	
	/**
	 * 获得引擎类型
	 * @param typeSN 类型标识
	 * @return
	 */
	public String getEngineType(String typeSN){
		if(StringUtils.isEmpty(typeSN)){
			typeSN="default";
		}
		return engineType.get(typeSN);
	}
	/**
	 * 获得元素类型，服务标识
	 * @param typeSN 类型标识
	 * @return
	 */
	public String getElementType(String typeSN){
		return elementType.get(typeSN);
	}
	/**
	 * 获得freemarker类型
	 * @param typeSN 类型标识
	 * @return
	 */
	public String getTemplateType(String typeSN){
		return templateType.get(typeSN);
	}
	/**
	 * 获得session参数的bean
	 * @return
	 */
	public Object getSessionObject(){
		return sessionComponet.getSessionObject();
	}
	
	//getter and setter
	public Map<String, String> getEngineType() {
		return engineType;
	}
	public void setEngineType(Map<String, String> engineType) {
		this.engineType = engineType;
	}
	public Map<String, String> getElementType() {
		return elementType;
	}
	public void setElementType(Map<String, String> elementType) {
		this.elementType = elementType;
	}
	
	public Map<String, String> getTemplateType() {
		return templateType;
	}
	public void setTemplateType(Map<String, String> templateType) {
		this.templateType = templateType;
	}
	public String getDefaultTemplateType() {
		return defaultTemplateType;
	}
	public void setDefaultTemplateType(String defaultTemplateType) {
		this.defaultTemplateType = defaultTemplateType;
	}
	public SessionParams getSessionComponet() {
		return sessionComponet;
	}
	public void setSessionComponet(SessionParams sessionComponet) {
		this.sessionComponet = sessionComponet;
	}
	
}
