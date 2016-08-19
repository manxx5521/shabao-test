package com.xiaoshabao.wechat.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信相关设置
 */
@Component("wechatConfig")
public class WechatConfig {
	/** 网址 */
	@Value("${wechat.domain}")
	private String domain;

	/** 获得token方式 */
	@Value("${wechat.tokenType}")
	private int tokenType;
	/** 是否开启token获取调度1开启 */
	@Value("${wechat.tokenJob}")
	private int tokenJob;

	/** 微信自动登录方式 */
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

	public int getTokenJob() {
		return tokenJob;
	}

	public void setTokenJob(int tokenJob) {
		this.tokenJob = tokenJob;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setTokenType(int tokenType) {
		this.tokenType = tokenType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

}
