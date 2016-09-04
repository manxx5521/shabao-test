package com.xiaoshabao.wechat.bean;

import com.xiaoshabao.wechat.entity.SubscriberEntity;

/**
 * 微信session
 */
public class WechatSession {
	/**
	 * 系统帐号id
	 */
	private Integer accountId;
	
	private String openid;
	/** 登录方式1-base和2-info两种 */
	private int type=0;
	/**
	 * 获得微信相关信息
	 */
	private SubscriberEntity info;
	
	public WechatSession(){
		
	}
	public WechatSession(Integer accountId){
		this.accountId=accountId;
	}
	
	/**
	 * 获得系统帐号id
	 * @return
	 */
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public SubscriberEntity getInfo() {
		return info;
	}
	public void setInfo(SubscriberEntity info) {
		this.info = info;
	}
	/** 登录方式1-base和2-info两种 */
	public int getType() {
		return type;
	}
	/** 登录方式1-base和2-info两种 */
	public void setType(int type) {
		this.type = type;
	}
}
