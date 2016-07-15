package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 微信模版表
 */
public class TemplateWx {
	/** 模版id */
	private Integer templateId;
	/** 模版类型，参考TEMPLATE_TYPE */
	private Integer templateType;
	/** 模版范围 */
	private String scope;
	/** 模版内容 */
	private String content;
	/** 模版更新时间 */
	private Date updateTime;
	/** 模版更新用户 */
	private Integer updateUser;
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public Integer getTemplateType() {
		return templateType;
	}
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	
}
