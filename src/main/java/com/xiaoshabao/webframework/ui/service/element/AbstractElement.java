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
	 * 获得元素参数
	 * <p>将文本JSON转换成Map,返回个JSONObject的父类</p>
	 * @param elementParams 元素JSON文本
	 * @param extParams	扩转JSON文本
	 * @return 不为空的结果
	 */
	public Map<String,Object> getElementParams(String elementParams,String extParams);
	
	
//------------------------
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
