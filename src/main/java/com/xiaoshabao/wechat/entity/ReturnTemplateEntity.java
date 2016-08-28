package com.xiaoshabao.wechat.entity;

import java.util.Date;

/**
 * 回复消息模版
 */
public class ReturnTemplateEntity {
	
	/** 模版id */
	private Integer templateId;
	/** 类型，1文本、9自定义freemarker */
	private Integer type;
	/** 模版描述 */
	private String des;
	/** 参数JSON参数 */
	private String params;
	
	private Integer updateUser;
	
	private Date updateTime;

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
