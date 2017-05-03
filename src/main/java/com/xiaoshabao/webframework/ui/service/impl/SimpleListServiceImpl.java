package com.xiaoshabao.webframework.ui.service.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.component.ApplicationContextUtil;
import com.xiaoshabao.baseframework.exception.MsgErrorException;
import com.xiaoshabao.webframework.ui.dto.BillListData;
import com.xiaoshabao.webframework.ui.dto.BillListDto;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
import com.xiaoshabao.webframework.ui.service.FormListService;
import com.xiaoshabao.webframework.ui.service.FormTemplateService;
import com.xiaoshabao.webframework.util.ResourceManager;

//简单列表引擎
@Service("simpleListService")
public class SimpleListServiceImpl extends AbstractFormListServiceImpl
		implements FormListService {

	// 获得list内容
	@Override
	public BillListData getBillList(BillListDto billListDto,Map<String, Object> params) {
		BillListData result=new BillListData(); 
		String templateEngine=this.getTemplateEngineType(billListDto.getTemplate().getEngineType());
		FormTemplateService templateService=ApplicationContextUtil.getBean(templateEngine, FormTemplateService.class);
		TemplateData templateData=templateService.getTemplate(billListDto.getTemplate(),params);
		if(!templateData.isSuccess()){
			throw new MsgErrorException(templateData.getMessage()==null?"模版渲染错误":templateData.getMessage());
		}
		
		Set<String> header=templateData.getHeader();
		result.setTemplateHtml(templateData.getContentHtml());

		//解析头部引用
		String[] headers=this.getHeaderHtml(header);
		result.setHeaderCSS(headers[0]);
		result.setHeaderJS(headers[1]);
		return result;
	}
	/**
	 * 获得头部引用
	 * @param header
	 * @return [css,js]两类
	 */
	public String[] getHeaderHtml(Set<String> header){
		StringBuilder css=new StringBuilder();
		StringBuilder js=new StringBuilder();
		if(header!=null&&header.size()>0){
			ResourceManager manager=ResourceManager.getInstance();
			for(String id:header){
				css.append(manager.getCssTag(id));
				js.append(manager.getJSTag(id));
			}
		}
		return new String[]{css.toString(),js.toString()};
	}

}