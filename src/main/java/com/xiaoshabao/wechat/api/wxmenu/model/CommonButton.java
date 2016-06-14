package com.xiaoshabao.wechat.api.wxmenu.model;

/**
 * 普通子类按钮-菜单最小单元。
 * 可以直接做一级菜单，不能有二级。
 * 或者是用来做二级菜单的 子菜单。
 */
public class CommonButton extends Button{
	/**
	 * 按钮类型：click 点击，view 视图跳转URL<br>
	 * 使用{@link com.xiaoshabao.wechat.api.wxmenu.MenuType}中的类型
	 */
	private String type;
	
	/**
	 * 菜单KEY值，用于消息接口推送
	 */
	private String key;
	
	/**
	 * view 视图跳转的URL
	 */
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	
	/**
	 * click 点击，view 视图跳转URL
	 */
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
