package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import com.xiaoshabao.webframework.ui.dto.BillListDto;

/**
 * 简单列表引擎
 * <p>
 *	
 * </p>
 */
public interface FormListService {
	/**
	 * 获得list内容
	 * @param billListDto
	 * @return
	 */
	public BillListDto getBillList(BillListDto billListDto,Map<String, Object> params);

}
