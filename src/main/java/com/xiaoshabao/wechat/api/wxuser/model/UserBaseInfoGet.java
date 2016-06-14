package com.xiaoshabao.wechat.api.wxuser.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 获取用户基本信息
 */
@ReqType("getUserBaseInfo")
public class UserBaseInfoGet extends WeixinReqParam {

	private String openid;

	/**
	 * 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 */
	private String lang;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
 

}
