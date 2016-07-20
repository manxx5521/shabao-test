package com.xiaoshabao.wechat.api.wxbase.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

@ReqType("authBaseInfo")
public class AuthBaseReq extends WeixinReqParam{
	private String appid;
	private String secret;
	private String code;
	private String grant_type="authorization_code";
	
	@Override
	public String toParams() {
		StringBuffer sb=new StringBuffer();
		sb.append("appid=").append(appid);
		sb.append("&secret=").append(secret);
		sb.append("&code=").append(code);
		sb.append("&grant_type=").append(grant_type);
		return sb.toString();
	}
	
	public AuthBaseReq() {
		
	}
	public AuthBaseReq(String appid, String secret, String code) {
		this.appid = appid;
		this.secret = secret;
		this.code = code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	
}
