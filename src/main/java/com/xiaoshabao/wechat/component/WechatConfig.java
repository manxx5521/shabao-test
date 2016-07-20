package com.xiaoshabao.wechat.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信相关设置
 */
@Component("wechatConfig")
public class WechatConfig{
	/**网址*/
	@Value("${wechat.domain}")
	private String domain;
	
	/**获得token方式*/
	@Value("${wechat.tokenType}")
	private int tokenType;
	
	/**微信自动登录方式*/
	@Value("${wechat.loginType}")
	private String loginType;

	public String getDomain() {
		return domain;
	}

	public int getTokenType() {
		return tokenType;
	}

	public String getLoginType() {
		return loginType;
	}
}
