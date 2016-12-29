package com.xiaoshabao.webframework.ui.service.element;

import java.util.Map;

import com.xiaoshabao.webframework.ui.entity.ElementEntity;
import com.xiaoshabao.webframework.ui.entity.TemplatElementEntity;
/**
 * element元素父类接口
 */
public interface AbstractElement {
	
	/**
	 * 初始化数据
	 * @param element 定义元素
	 */
	public void initData(ElementEntity element);
	/**
	 * 初始化数据
	 * @param templateElement 表单参数
	 * @param element 定义元素
	 */
	public void initData(TemplatElementEntity templateElement,ElementEntity element);
	/**
	 * 设置元素bean参数
	 * @param params
	 */
	public void setBeanParams(Map<String,Object> params);

}
