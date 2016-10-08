package com.xiaoshabao.webframework.ui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.webframework.ui.dao.ElementDao;
import com.xiaoshabao.webframework.ui.dao.UIElementDao;
import com.xiaoshabao.webframework.ui.element.AbstractUIElement;
import com.xiaoshabao.webframework.ui.element.UIElement;
import com.xiaoshabao.webframework.ui.element.UIElementFactory;
import com.xiaoshabao.webframework.ui.entity.FormElement;
import com.xiaoshabao.webframework.ui.entity.FormElementDef;
import com.xiaoshabao.webframework.ui.service.UIElementService;

@Service("UIElementService")
public class UIElementServiceImpl implements UIElementService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ElementDao elementDao;
	
	
	@Autowired
	protected UIElementDao UIElementDao;
	private String templateid = "";
	private String elementid = "";
	private List<UIElement> elements = new ArrayList<UIElement>();
	private List<FormElement> formelememts = new ArrayList<FormElement>();
	
	//获得表单元素
	@Override
	public <T extends AbstractUIElement<?>> T getElement(Long elementId, Class<T> clazz){
		T t=null;
		try {
			FormElementDef formElementDef = this.elementDao.getElementDef(elementId);
			FormElement elementi = new FormElement();
			elementi.setFormElementDef(formElementDef);
			elementi.setElementid(formElementDef.getId());
			t=clazz.newInstance();
			t.setFormElement(elementi, clazz);
		} catch (Exception e) {
			logger.error("表单元素初始化时失败");
			e.printStackTrace();
		}
		return t;
	}
	/*****************************/
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElementService#getElement(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public UIElement getElement(String templateid, Long elementid)
			throws ServiceException {
		initElements(templateid);
		// TODO Auto-generated method stub
		for (UIElement element : elements) {
			if (element.getFormElement().getElementid() == elementid) {
				return element;
			}
		}
		return null;
	}

	private void initElements(String templateid) throws ServiceException {
		if (templateid == null || templateid.equalsIgnoreCase("")) {
			throw new ServiceException("表单id不正确。");
		}
		//if (!this.templateid.equalsIgnoreCase(templateid)) {
			this.templateid = templateid;
			formelememts = UIElementDao.getElements(templateid);
			elements.clear();
			for (FormElement formElement : formelememts) {
				UIElement uiElement = UIElementFactory.getIntance()
						.createUIElement(formElement);
				elements.add(uiElement);
			}
			// 重新初始化elements
		//}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElementService#getElements(java.lang
	 * .String)
	 */
	@Override
	public List<UIElement> getElements(String templateid)
			throws ServiceException {
		// TODO Auto-generated method stub
		initElements(templateid);
		// TODO Auto-generated method stub
		return this.elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.ui.form.UIElementService#getElement(java.lang
	 * .Long)
	 */
	@Override
	public UIElement getElement(Long elementid) throws ServiceException {
		// TODO Auto-generated method stub
		FormElementDef formElementDef = this.UIElementDao
				.getElementDef(elementid);
		FormElement elementi = new FormElement();
		elementi.setFormElementDef(formElementDef);
		elementi.setElementid(formElementDef.getId());
		UIElement uiElement = UIElementFactory.getIntance().createUIElement(
				elementi);
		return uiElement;
	}

	/**
	 * @return the templateid
	 */
	public String getTemplateid() {
		return templateid;
	}

	/**
	 * @param templateid
	 *            the templateid to set
	 */
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	/**
	 * @return the elementid
	 */
	public String getElementid() {
		return elementid;
	}

	/**
	 * @param elementid
	 *            the elementid to set
	 */
	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	/**
	 * @return the elements
	 */
	public List<UIElement> getElements() {
		return elements;
	}

	/**
	 * @param elements
	 *            the elements to set
	 */
	public void setElements(List<UIElement> elements) {
		this.elements = elements;
	}

	

}
