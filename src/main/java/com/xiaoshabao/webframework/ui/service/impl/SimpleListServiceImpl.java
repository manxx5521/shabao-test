package com.xiaoshabao.webframework.ui.service.impl;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.webframework.ui.dto.BillListDto;
import com.xiaoshabao.webframework.ui.service.FormListService;
import com.xiaoshabao.webframework.ui.service.FormTemplateService;

//简单列表引擎
@Service("simpleListService")
public class SimpleListServiceImpl extends AbstractFormListServiceImpl
		implements FormListService {

	// 获得list内容
	@Override
	public BillListDto getBillList(BillListDto billListDto) {
		String templateEngine=this.getTemplateEngineType(billListDto.getList().getEngineType());
		FormTemplateService templateService=ApplicationContextUtil.getBean(templateEngine, FormTemplateService.class);
		
		return null;
	}

}
