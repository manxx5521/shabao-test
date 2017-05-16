package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BillListData;
import com.xiaoshabao.webframework.ui.dto.BillListDto;
import com.xiaoshabao.webframework.ui.entity.ListEntity;

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
	public BillListData getBillList(BillListDto billListDto,Map<String, Object> params);
	/**
	 * 查询列表数据
	 * <p>data中会带有{@link com.xiaoshabao.webframework.ui.dto.DataTablesParams}中的参数</p>
	 * @param billId
	 * @param listEntity
	 * @param data
	 * @return
	 */
	public AjaxResult queryList(String billId,ListEntity listEntity, Map<String, Object> data);

}
