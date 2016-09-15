package com.xiaoshabao.webframework.ui.service;

import java.util.List;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.webframework.ui.element.AbstractUIElement;
import com.xiaoshabao.webframework.ui.element.UIElement;
/**
 * 组件服务
 * @author dell
 *
 */
public interface UIElementService {
	/**
	 * 组件id
	 * @param elementid
	 * @return
	 * @throws ServiceException
	 */
	public <T extends AbstractUIElement<?>> T getElement(Long elementId,Class<T> clazz);
	
	
	
	
	/*****************/
	
	/**
	 * 组件id
	 * @param elementid
	 * @return
	 * @throws ServiceException
	 */
	public UIElement getElement(Long elementid) throws ServiceException;
	/**
	 * 获取组件
	 * @param templateid
	 * @param elementid
	 * @return
	 */
	public UIElement getElement(String templateid, Long elementid) throws ServiceException;

	/**
	 * 获取组件列表
	 * 
	 * @param templateid
	 * @return
	 */
	public List<UIElement> getElements(String templateid) throws ServiceException;
}
