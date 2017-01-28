package com.xiaoshabao.webframework.ui.entity;
/**
 * 模版元素实体
 */
public class TemplatElementEntity {
	/** 模版id */
	private String templateId;
	/** 元素id */
	private String elementId;
	/** 表单类型 condition tab list等 */
	private String formType;
	/** 表单key元素名字 */
	private String formKey;
	/** 表单显示 */
	private String label;
	/** 表单个性参数 */
	private String fromParams;
	/** 描述 */
	private String fromDesc;
	/** 排序 */
	private Integer orderNo;
	/** 是否必填 **/
	private Integer required;
	/** 显示模版类型 */
	private String viewType;
	/** 只读模版 **/
	private String readType;
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getFromParams() {
		return fromParams;
	}
	public void setFromParams(String fromParams) {
		this.fromParams = fromParams;
	}
	public String getFromDesc() {
		return fromDesc;
	}
	public void setFromDesc(String fromDesc) {
		this.fromDesc = fromDesc;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getRequired() {
		return required;
	}
	public void setRequired(Integer required) {
		this.required = required;
	}
	
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getReadType() {
		return readType;
	}
	public void setReadType(String readType) {
		this.readType = readType;
	}
	
}
