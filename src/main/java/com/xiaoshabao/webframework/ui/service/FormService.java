package com.xiaoshabao.webframework.ui.service;

import com.xiaoshabao.webframework.ui.dto.TemplateData;

public interface FormService {
	/**
	 * 获得模版数据
	 * @param templateId 模版id-必传
	 * @return
	 */
	public TemplateData getTemplateData(String templateId);
}
