package com.xiaoshabao.webframework.ui.service;

import java.util.List;

import com.xiaoshabao.webframework.ui.dto.SelectResultDto;
/**
 * 表单元素web请求服务
 */
public interface WebElementService{
	/**
	 * 获得web下拉列表数据
	 * @param elementId
	 * @return
	 */
	public List<SelectResultDto> getSelectValues(Integer elementId);

}