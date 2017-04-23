package com.xiaoshabao.webframework.ui.service.element;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplateElementEntity;
/**
 * element元素父类接口
 */
public interface AbstractElement {
	
	/**
	 * 初始化数据
	 * @param params 参数
	 * @param element 定义元素
	 */
	public JSONObject initData(Map<String,Object> params,ElementEntity element);
	/**
	 * 初始化数据
	 * @param templateElement 表单参数
	 * @param params 参数
	 * @param element 定义元素
	 */
	public JSONObject initData(Map<String,Object> params,TemplateElementEntity templateElement,ElementEntity element);

	/**
	 * 设置session参数
	 * @param params
	 */
	public void setSessionParams(Map<String,Object> params,ElementEntity element);
	
}
