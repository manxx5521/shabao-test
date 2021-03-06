package com.xiaoshabao.webframework.ui.service;

import java.util.Map;

import com.xiaoshabao.webframework.dto.AjaxResult;
import com.xiaoshabao.webframework.ui.dto.BillListData;
import com.xiaoshabao.webframework.ui.dto.BillViewData;
import com.xiaoshabao.webframework.ui.dto.TemplateData;
/**
 * 表单服务
 */
public interface FormService {
	/**
	 * 获得list界面数据
	 * @param billId
	 * @param data
	 * @return
	 */
	public BillListData getList(String billId,Map<String, Object> data);
	
	/**
	 * 查询列表
	 * @param billId
	 * @param data
	 * @return
	 */
  public AjaxResult queryList(String listId,Map<String, Object> data);
  
	/**
	 * 列表界面按钮功能操作
	 * 
	 * @param buttonId
	 * @param data
	 * @return
	 */
	public AjaxResult doButtonList(String buttonId, Map<String, Object> data);

	/**
	 * 获得view界面数据
	 * @param billId
	 * @param data
	 * @return
	 */
	public BillViewData getView(String billId, Map<String, Object> data);
	
	
	/**
	 * 视图界面按钮功能操作
	 * 
	 * @param buttonId
	 * @param data
	 * @return
	 */
	public AjaxResult doButtonView(String billId,String buttonId, Map<String, Object> data);
  
  
//  -------------------------------
	/**
	 * 获得模版数据
	 * @param templateId 模版id
	 * @return
	 */
	public TemplateData getTemplateData(String templateId);
	
	/**
	 *  相应元素web请求
	 * @param engineType 引擎类型
	 * @param elementId 元素id
	 * @param params 表单参数
	 * @return
	 */
	public AjaxResult getElementResponse(String engineType,String elementId,Map<String, Object> params);
}
