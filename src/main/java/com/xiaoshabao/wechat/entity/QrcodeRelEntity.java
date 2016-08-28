package com.xiaoshabao.wechat.entity;
/**
 * 二维码映射
 */
public class QrcodeRelEntity {
	
	private Integer qrcodeId;
	private Integer accountId;
	/** bargain看见对应QRCODE_TYPE */
	private String type;
	private String params;
	public Integer getQrcodeId() {
		return qrcodeId;
	}
	public void setQrcodeId(Integer qrcodeId) {
		this.qrcodeId = qrcodeId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
}
