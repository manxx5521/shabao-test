package com.xiaoshabao.webframework.ui.entity;
/**
 * 表单模版实体
 */
public class TemplateEntity {
	/** 模版id **/
	private String templateId;
	/** 模版名字 **/
	private String templateName;
	
	/**模版引擎类型*/
	private String templateEngine;
	
	private String tableId;
	
	private String remark;
	
	/**********************/
	/** 模版描述 **/
	private String templateDesc;
	/** 模版引擎类型 **/
	private String engineType;
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateDesc() {
		return templateDesc;
	}
	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTemplateEngine() {
		return templateEngine;
	}
	public void setTemplateEngine(String templateEngine) {
		this.templateEngine = templateEngine;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
}
