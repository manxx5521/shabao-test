package com.xiaoshabao.wechat.api.wxmenu.model;

import java.util.List;
/**
 * 菜单列表实体类
 */
public class WeixinButton {
	/**
	 * 菜单名字
	 */
	private String name;
	
	/**
	 * 菜单类型
	 * 使用{@link com.xiaoshabao.wechat.api.wxmenu.MenuType}中的类型
	 */
	private String type;
	
	/**
	 * 菜单的key,key为消息回调是的识别码。当菜单为click等类型时，并且本菜单为最末级菜单时有效。
	 */
	private String key;
	/**
	 * 点击菜单后跳转的URL。菜单为view时，并且本菜单为最末级菜单时有效。
	 */
	private String url;
	
	/**
	 * 菜单的素材id,当菜单为media_id类型和view_limited必须、有用，本菜单为最末级级菜单时有效。
	 */
	private String media_id;
	
	/**
	 * 子菜单，当有二级菜单时有效
	 */
	private List<WeixinButton> sub_button;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<WeixinButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<WeixinButton> sub_button) {
		this.sub_button = sub_button;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	
}
