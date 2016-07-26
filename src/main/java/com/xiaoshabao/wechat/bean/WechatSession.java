package com.xiaoshabao.wechat.bean;
/**
 * 微信session
 */
public class WechatSession {
	/**
	 * 系统帐号id
	 */
	private Integer accountId;
	
	private String openid;
	
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
}
