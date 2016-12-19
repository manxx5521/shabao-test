package com.xiaoshabao.webframework.ui.entity;
/**
 * 元素实体
 */
public class ElementEntity {
	/** 元素id */
	private String elementId;
	/** 元素名称 */
	private String elementName;
	/** 元素类型，比如tree、select等 */
	private String elementType;
	/** 描述 */
	private String elementDesc;
	/** 版本 */
	private Integer version;
	/** 参数JSON形式 */
	private String params;
	/** 显示模版 **/
	private String viewTemplate;
	
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public String getElementDesc() {
		return elementDesc;
	}
	public void setElementDesc(String elementDesc) {
		this.elementDesc = elementDesc;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getViewTemplate() {
		return viewTemplate;
	}
	public void setViewTemplate(String viewTemplate) {
		this.viewTemplate = viewTemplate;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
}
