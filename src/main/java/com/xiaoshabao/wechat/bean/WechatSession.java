package com.xiaoshabao.wechat.bean;
/**
 * 微信session
 */
public class WechatSession {
	/**
	 * 系统帐号id
	 */
	private Integer accountId;
	
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
	
}
