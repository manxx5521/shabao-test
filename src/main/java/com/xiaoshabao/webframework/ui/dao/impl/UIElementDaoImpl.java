package com.xiaoshabao.webframework.ui.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoshabao.baseframework.dao.impl.MybatisBaseDaoImpl;
import com.xiaoshabao.webframework.ui.dao.UIElementDao;
import com.xiaoshabao.webframework.ui.entity.FormElement;
import com.xiaoshabao.webframework.ui.entity.FormElementDef;

@Repository
public class UIElementDaoImpl extends MybatisBaseDaoImpl implements UIElementDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.dao.UIElementDao#getElementDef(java.lang.Long)
	 */
	@Override
	public FormElementDef getElementDef(Long elementid) {
		// TODO Auto-generated method stub
		return this.getDataSingle(FormElementDef.class, elementid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.dao.UIElementDao#getElement(java.lang.String,
	 * java.lang.Long)
	 */
	@Override
	public FormElement getElement(String templateid, Long elementid) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("templateid", templateid);
		params.put("elementid", elementid);
		FormElement formElement = this.getDataSingle(FormElement.class, params);
		FormElementDef formElementDef = this.getDataSingle(
				FormElementDef.class, elementid);
		formElement.setFormElementDef(formElementDef);
		return formElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asiainfo.webframework.dao.UIElementDao#getElements(java.lang.String)
	 */
	@Override
	public List<FormElement> getElements(String templateid) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("templateid", templateid);
		List<FormElement> elements = this.getData(FormElement.class, params);
		for (FormElement formElement : elements) {
			FormElementDef formElementDef = this.getElementDef(formElement
					.getElementid());
			formElement.setFormElementDef(formElementDef);
		}
		return elements;
	}

}
