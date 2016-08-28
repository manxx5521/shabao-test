package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.wechat.entity.ReturnMessageEntity;
import com.xiaoshabao.wechat.entity.ReturnTemplateEntity;

/**
 * 响应回复
 */
public class ReturnMessageDto extends ReturnMessageEntity{
	/**
	 * 模版数据
	 */
	private ReturnTemplateEntity template;

	public ReturnTemplateEntity getTemplate() {
		return template;
	}

	public void setTemplate(ReturnTemplateEntity template) {
		this.template = template;
	}
	
}
