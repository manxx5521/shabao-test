package com.xiaoshabao.webframework.ui.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.TableEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.service.FormTemplateService;
@Service("simpleTemplateService")
public class SimpleTemplateServiceImpl extends AbstractTemplateServiceImpl
		implements FormTemplateService {
	//获取模版数据
	@Override
	public TemplateData getTemplate(TemplateEntity template,Map<String, Object> data,boolean isLoadWhere) {
		if(template==null||StringUtils.isEmpty(template.getTemplateId())){
			logger.error("未能找到对应模版,templateId={}",template.getTemplateId());
			throw new MsgErrorException("未能找到对应模版");
		}
		return getTemplateElements(template,data,isLoadWhere);
	}
	
	 @Override
	 public String getTemplateQuerySQL(String tableName,String templateId,Map<String, Object> data) {
	    return super.getTemplateQuerySQL(tableName,templateId, data);
	 }


}
