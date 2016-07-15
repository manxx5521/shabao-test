package com.xiaoshabao.wechat.dto;



/**
 * 微信接入参数封装
 */
public class WechatInDto {
	/** 微信加密签名-明文 */
	private String signature;
	/** 微信加密签名-加密 */
	private String msg_signature;
	/** 随机字符串 */
	private String echostr;
	/** 时间戳 */
	private String timestamp;
	/** 随机数 */
	private String nonce;
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getMsg_signature() {
		return msg_signature;
	}
	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
}
