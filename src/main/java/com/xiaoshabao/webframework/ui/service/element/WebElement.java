package com.xiaoshabao.webframework.ui.service.element;

import java.util.Map;

import com.xiaoshabao.webframework.dto.AjaxResult;

/**
 * web接口，需要ajax请求数据的元素需要实现
 */
public interface WebElement extends AbstractElement{
	/**
	 * web数据AJAX响应
	 * @param params
	 * @return
	 */
	public AjaxResult getElementResponse(Map<String, Object> params);
}
