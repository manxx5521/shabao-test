package com.xiaoshabao.webframework.ui.service;

import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;

/**
 * 表单模版服务
 */
public interface FormTemplateService {
	/**
	 * 获取模版数据
	 * @param template
	 * @return
	 */
	public TemplateData getTemplate(TemplateEntity template);

}
