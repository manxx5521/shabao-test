package com.xiaoshabao.webframework.ui.component;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.component.SessionParams;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.service.FormSessionService;

/**
 * 表单引擎组件(XML注入)
 */
@Component("formEngineComponet")
public class FormEngineComponet {
	
	/** 引擎类型 **/
	private Map<String,String> elementSerivceType;
	/** session服务 **/
	private FormSessionService formSessionService;
	
	/**
	 * 获得元素服务类型
	 * @param elementType 元素类型select等
	 * @return
	 */
	public String getElementSerivceType(String elementType) {
		String elementSerivce=  elementSerivceType.get(elementType);
		if(elementSerivce==null){
			throw new ServiceException("未能根据元素类型 "+engineType+" 获得服务类型");
		}
		return elementSerivce;
	}
	
	public Map<String, String> getElementSerivceType() {
		return elementSerivceType;
	}
	public void setElementSerivceType(Map<String, String> elementSerivceType) {
		this.elementSerivceType = elementSerivceType;
	}
	
	public FormSessionService getFormSessionService() {
		return formSessionService;
	}

	public void setFormSessionService(FormSessionService formSessionService) {
		this.formSessionService = formSessionService;
	}
	
	

	
	
	
	
	
//	---------------------

	




	







	







	/** 引擎类型 **/
	private Map<String,String> engineType;
	/** 元素类型 **/
	private Map<String,String> elementType;
	/** freemarker模版类型 **/
	private Map<String,String> templateType;
	/** 默认freemarker模版类型 **/
	private String defaultTemplateType="viewTemplate";
	/** 默认引擎类型 **/
	private String defaultEngineType="default";
	/**获得子项目session**/
	private SessionParams sessionComponet;
	
	
	
	
	
	/**
	 * 获得引擎类型
	 * @param typeSN 类型标识
	 * @return
	 */
	public String getEngineType(String typeSN){
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
	
	/**
	 * 向表单数据要添加的template值
	 * @param data
	 * @param template
	 */
	public void putTemplateData(Map<String,Object> data,TemplateEntity template){
		data.put("engineType", template.getEngineType());
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
	public String getDefaultEngineType() {
		return defaultEngineType;
	}
	public void setDefaultEngineType(String defaultEngineType) {
		this.defaultEngineType = defaultEngineType;
	}
	
}
