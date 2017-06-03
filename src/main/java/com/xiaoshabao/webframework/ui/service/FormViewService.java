package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import com.xiaoshabao.webframework.ui.dto.BillDto;
import com.xiaoshabao.webframework.ui.dto.BillViewData;

/**
 * 视图
 * <p>
 *	
 * </p>
 */
public interface FormViewService {
	/**
	 * 获得view界面数据
	 * @param billId
	 * @param data
	 * @return
	 */
	public BillViewData getView(BillDto billDto, Map<String, Object> data);

}
