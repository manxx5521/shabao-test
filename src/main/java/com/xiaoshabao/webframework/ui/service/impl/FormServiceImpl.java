package com.xiaoshabao.webframework.ui.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateEntity;
import com.xiaoshabao.webframework.ui.service.FormService;
import com.xiaoshabao.webframework.ui.service.TemplateFactory;
/**
 * 表单服务
 */
@Service("formServiceImpl")
public class FormServiceImpl extends AbstractTemplateServiceImpl implements FormService {
	
	// 获得模版数据
	@Override
	public TemplateData getTemplateData(String templateId) {
		TemplateEntity templateEntity=this.elementDao.getTemplateByid(templateId);
		if(templateEntity==null){
			logger.info("模版渲染失败，未根据模版id获得模版，失败模版id为{}",templateId);
			return null;
		}
		if(StringUtils.isEmpty(templateEntity.getEngineType())){
			templateEntity.setEngineType(this.formEngineComponet.getDefaultEngineType());
		}
		String engineType=formEngineComponet.getEngineType(templateEntity.getEngineType());
		if(StringUtils.isEmpty(engineType)){
			logger.info("模版渲染失败，未摸得渲染引擎类型，失败模版id为{}",templateId);
			return null;
		}
		TemplateFactory templateFactory=ApplicationContextUtil.getBean(engineType, TemplateFactory.class);
		TemplateData templateData=new TemplateData();
		Map<String, Object> params=new HashMap<String, Object>();
		this.formEngineComponet.putTemplateData(params, templateEntity);
		templateData.setHtml(templateFactory.getTemplateElements(templateId,params));
		return templateData;
	}
	
	// 相应元素web请求
	@Override
	public AjaxResult getElementResponse(String engineType,String elementId,Map<String, Object> params) {
		ElementEntity element = this.elementDao.getElementById(elementId);
		if (element==null) {
			logger.error("未找到元素id对应的元素，请查看元素id填写是否正确；元素id{}。", elementId);
			return new AjaxResult("元素类型错误");
		}
		
		engineType=formEngineComponet.getEngineType(engineType);
		if(StringUtils.isEmpty(engineType)){
			logger.info("模版渲染失败，未摸得渲染引擎类型，失败模版id为{}",elementId);
			return null;
		}
		TemplateFactory templateFactory=ApplicationContextUtil.getBean(engineType, TemplateFactory.class);
		return templateFactory.getElementResponse(element, params);
	}
	

}
