package com.xiaoshabao.webframework.ui.element;

import java.util.Map;

import com.xiaoshabao.webframework.ui.entity.FormElement;
import com.xiaoshabao.webframework.ui.entity.SelectElementDef;
import com.xiaoshabao.webframework.ui.exception.InvalidConfigException;
import com.xiaoshabao.webframework.ui.exception.InvalidValueException;


public interface UIElement {
	/**
	 * 获取组件配置
	 * 
	 * @param elementid
	 * @return
	 */
	public FormElement getFormElement();
	
	/**
	 * 组件渲染1
	 * 
	 * @param templateid
	 * @param elementid
	 * @param params
	 * @return
	 */
	public Object render(
			Map params) throws InvalidConfigException;

	/**
	 * 组件渲染2
	 * 
	 * @param templateid
	 * @param elementid
	 * @param params
	 * @return
	 */
	public Object renderview(
			Map params) throws InvalidConfigException;;

	/**
	 * 校验数据有效性,做基本的非空校验和输入长度校验和正则表达式校验，
	 * 其他校验开发人员根据自己情况扩展
	 * @param templateid
	 * @param elementid
	 * @param formData
	 */
	public void validate(
			Map formData) throws  InvalidValueException;
    /**
     * 数格式化
     * @param templateid
     * @param elementid
     * @param formData
     * @return
     */
	public Object formater(
			Map formData);
	/**
	 * 正向转换
	 * @param value
	 * @return
	 */
	public Object forwardConverter(String value);
	/**
	 * 反向转换
	 * @param value
	 * @return
	 */
	public String reverseConverter(Object value);
	
	public Class<?> getdefClass();
}
