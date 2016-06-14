package com.xiaoshabao.wechat.api.wxbase.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;


@ReqType("access_token")
public class AccessToken extends WeixinReqParam{

	/**
	 * 获取access_token填写client_credential
	 */
	private String grant_type="client_credential";
	
	/**
	 * 第三方用户唯一凭证
	 */
	private String appid;
	
	/**
	 * 第三方用户唯一凭证密钥，即appsecret
	 */
	private String secret;
	
	@Override
	public String toParams() {
		StringBuffer sb=new StringBuffer();
		sb.append("grant_type=client_credential");
		sb.append("&appid=");
		sb.append(appid);
		sb.append("&secret=");
		sb.append(secret);
		return sb.toString();
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	 
}
