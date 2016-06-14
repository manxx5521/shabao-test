package com.xiaoshabao.wechat.api.wxaccount.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 生成短连接
 */
@ReqType("shorturlCreate")
public class ShortUrlCreate extends WeixinReqParam {
	/**
	 * 此处填long2short，代表长链接转短链接
	 */
	private String action = "long2short";
	/**
	 * 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的URL
	 */
	private String long_url;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLong_url() {
		return long_url;
	}

	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}
	
}
