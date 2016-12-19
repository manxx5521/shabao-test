package com.xiaoshabao.webframework.ui.component;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 表单引擎组件(XML注入)
 */
public class FormEngineComponet {
	/** 引擎类型 **/
	private Map<String,String> engineType;
	/** 元素类型 **/
	private Map<String,String> elementType;
	/** 默认元素类型 **/
	private String defaultElementType="defaultUIElement";
	
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
	public String getDefaultElementType() {
		return defaultElementType;
	}
	public void setDefaultElementType(String defaultElementType) {
		this.defaultElementType = defaultElementType;
	}
}
