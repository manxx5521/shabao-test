package com.xiaoshabao.webframework.ui.entity;
/**
 * 模版元素实体
 */
public class TemplateElementEntity {
	/** 模版id */
	private String templateId;
	/** 元素id */
	private String elementId;
	/** 表单显示 */
	private String label;
	
	private String columnId;
	
	private String elementParams;
	/** 默认值 **/
	private String defaultValue;
	/** 是否必填 **/
	private Integer required;
	/** 最大长度 */
	private Integer maxLength;
	/** 最小长度 */
	private Integer minLength;
	/** 是否使用 */
	private Boolean isUsed;
	/** 是否只读*/
	private Boolean isReadOnly;
	/** 是否显示 */
	private Boolean isDisplay;
	
	private String remark;
	
	/** 排序 */
	private Integer orderNo;
	
	
//----------------------------------------
	/** 表单类型 condition tab list等 */
	private String formType;
	/** 表单key元素名字 */
	private String formKey;
	
	/** 表单个性参数 */
	private String fromParams;
	/** 描述 */
	private String fromDesc;
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
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getElementParams() {
		return elementParams;
	}
	public void setElementParams(String elementParams) {
		this.elementParams = elementParams;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public Integer getMinLength() {
		return minLength;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	public Boolean getIsReadOnly() {
		return isReadOnly;
	}
	public void setIsReadOnly(Boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	public Boolean getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
	
}
