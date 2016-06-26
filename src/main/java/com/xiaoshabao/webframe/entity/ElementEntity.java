package com.xiaoshabao.webframe.entity;
/**
 * 元素 实体
 */
public class ElementEntity {
	/** 元素id */
	private Integer elementId;
	/** 元素名称 */
	private String elementName;
	/** 元素类型，比如tree、select等 */
	private String elementType;
	/** 描述 */
	private String elementDescribe;
	/** 版本 */
	private Integer version;
	/** 参数JSON形式 */
	private String param;
	
	public Integer getElementId() {
		return elementId;
	}
	public void setElementId(Integer elementId) {
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
	public String getElementDescribe() {
		return elementDescribe;
	}
	public void setElementDescribe(String elementDescribe) {
		this.elementDescribe = elementDescribe;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
}
