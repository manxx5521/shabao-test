package com.xiaoshabao.webframework.ui.element;

import java.util.Map;

import com.xiaoshabao.webframework.ui.entity.FormElement;
import com.xiaoshabao.webframework.ui.entity.SelectElementDef;

/**
 * 下拉框
 */
public class SelectUIElement extends AbstractUIElement<SelectElementDef> {
	
	public SelectUIElement() {
	}
	
	public SelectUIElement(FormElement formElement) {
		super(formElement);
	}

	@Override
	protected Map getCustomRenderParam(Map formdata) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Class<SelectElementDef> getdefClass() {
		return SelectElementDef.class;
	}
	
}
