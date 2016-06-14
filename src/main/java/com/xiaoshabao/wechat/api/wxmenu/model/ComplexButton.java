package com.xiaoshabao.wechat.api.wxmenu.model;
/**
 * 复杂的父类按钮，用来装子类按钮
 */
public class ComplexButton extends Button{
	/**
	 * 子类菜单的集合
	 */
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
