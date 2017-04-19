package com.xiaoshabao.webframework.ui.service.impl;

import org.springframework.stereotype.Service;

import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.service.FormTemplateService;
@Service("simpleTemplateService")
public class SimpleTemplateServiceImpl extends AbstractTemplateServiceImpl
		implements FormTemplateService {
	//获取模版数据
	@Override
	public TemplateData getTemplate(TemplateEntity template) {
		// TODO Auto-generated method stub
		return null;
	}

}
