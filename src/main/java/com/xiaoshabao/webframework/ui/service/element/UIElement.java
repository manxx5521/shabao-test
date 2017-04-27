package com.xiaoshabao.webframework.ui.service.element;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateElementEntity;

public interface UIElement extends AbstractElement{
	
	public String validateData();
	
//-----------------------
	/**
	 * 无参数render
	 */
	public String render(Map<String,Object> params,ElementEntity element);
	
	/**
	 * render,按类型
	 * @param params
	 * @param templateTypeName
	 * @return
	 */
	public String render(Map<String,Object> params,ElementEntity element,String templateTypeName);
	
	/**
	 * 设置公共参数
	 * @param params
	 */
	public void setPublicProperties(Map<String,Object> params,TemplateElementEntity tempalteElement,ElementEntity element);
	
	/**
	 * 设置元素自定义参数
	 * @param params
	 */
	public void setCustomParams(Map<String,Object> params,JSONObject paramJSON);

}
