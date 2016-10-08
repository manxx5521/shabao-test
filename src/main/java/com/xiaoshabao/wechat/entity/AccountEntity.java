package com.xiaoshabao.wechat.entity;


/**
 * 微信帐号信息<br/>
 * 获得token用的实体是AccessToken
 * 
 */
public class AccountEntity extends AccessToken{
	/**所属部门*/
	private String departId;

	/**
	 * 状态 0未接入，1正常，3失效
	 */
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
}
