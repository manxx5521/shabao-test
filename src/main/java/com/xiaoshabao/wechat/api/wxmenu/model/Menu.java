package com.xiaoshabao.wechat.api.wxmenu.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 菜单实例，用来装button
 */
@ReqType("menuCreate")
public class Menu extends WeixinReqParam{
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
}
