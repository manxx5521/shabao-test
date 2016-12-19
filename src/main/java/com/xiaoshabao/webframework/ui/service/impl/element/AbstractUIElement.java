package com.xiaoshabao.webframework.ui.service.impl.element;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.component.TemplateEngine;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplatElementEntity;
import com.xiaoshabao.webframework.ui.service.element.UIElement;
import com.xiaoshabao.webframework.ui.service.impl.AbstractTemplateServiceImpl;
/**
 * 元素抽象类<br>
 * 添加表单元素时，继承的类
 */
public abstract class AbstractUIElement extends AbstractTemplateServiceImpl implements UIElement {
	/** 关联表元素 **/
	private TemplatElementEntity tempalteElement;
	/** 组件元素数据 **/
	private ElementEntity element;
	/** 元素参数json,可以用来转换为子类参数类型 **/
	private JSONObject paramJSON;
	
	//初始化数据
	@Override
	public void initData(TemplatElementEntity tempalteElement,ElementEntity element){
		this.element=element;
		this.tempalteElement=tempalteElement;
	}
	/**
	 * 设置JOSN参数
	 */
	@Override
	public void setJSONParams(Map<String,Object> params){
		String elementParam=element.getParams();
		if(StringUtils.isNotEmpty(elementParam)){
			JSONObject elementJSON=JSONObject.parseObject(elementParam);
			params.putAll(elementJSON);
		}
		String formParam=tempalteElement.getFromParams();
		if(StringUtils.isNotEmpty(formParam)){
			JSONObject formJSON = JSON.parseObject(formParam);
			if(paramJSON==null){
				paramJSON=formJSON;
			}else{
				paramJSON.putAll(formJSON);
			}
			params.putAll(formJSON);
		}
	}
	
	@Override
	public String render(Map<String,Object> params) {
		try {
			return TemplateEngine.renderTemplate(this.element.getElementId(), this.element.getViewTemplate(), params);
		} catch (ServiceException e) {
			logger.error("render异常 template {} element {}",this.tempalteElement==null?"null":this.tempalteElement.getTemplateId(),
					this.element.getElementId(),e);
			return "";
		}
	}
	@Override
	public void setPublicProperties(Map<String,Object> params){
		
	}
	@Override
	public void setCustomParams(Map<String,Object> params){
		
	}
	
	//清空便于内存回收
	@Override
	public void clear() {
		this.element=null;
		this.tempalteElement=null;
		this.paramJSON=null;
	}
}
