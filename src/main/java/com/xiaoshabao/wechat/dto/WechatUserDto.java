package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.wechat.entity.SubscriberEntity;

/**
 * 微信用户信息
 */
public class WechatUserDto extends SubscriberEntity{
	
	/** 应用名 */
	private String appName;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
