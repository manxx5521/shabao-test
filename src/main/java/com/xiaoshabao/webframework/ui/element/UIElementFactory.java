package com.xiaoshabao.webframework.ui.element;

import com.xiaoshabao.webframework.ui.entity.FormElement;

/**
 * 输入域组件工厂
 * 
 * @author baowzh
 *
 */
public class UIElementFactory {
	private static UIElementFactory intance = new UIElementFactory();

	public UIElement createUIElement(FormElement formElement) {
		UIElement element = null;
		if (formElement.getFormElementDef().getType()
				.equalsIgnoreCase("select")) {
			element = new SelectUIElement(formElement);
		}
		return element;
	}

	/**
	 * @return the intance
	 */
	public static UIElementFactory getIntance() {
		return intance;
	}

}
