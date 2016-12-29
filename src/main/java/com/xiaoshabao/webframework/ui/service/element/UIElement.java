package com.xiaoshabao.webframework.ui.service.element;

import java.util.Map;

public interface UIElement extends AbstractElement{
	/**
	 * 无参数render
	 */
	public String render(Map<String,Object> params);
	
	/**
	 * render,按类型
	 * @param params
	 * @param templateTypeName
	 * @return
	 */
	public String render(Map<String,Object> params,String templateTypeName);
	
	/**
	 * 设置公共参数
	 * @param params
	 */
	public void setPublicProperties(Map<String,Object> params);
	/**
	 * 设置元素自定义参数
	 * @param params
	 */
	public void setCustomParams(Map<String,Object> params);
	
	/**
	 * 清空元素，便于内存回收
	 */
	public void clear();

}
