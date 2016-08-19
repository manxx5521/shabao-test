package com.xiaoshabao.wechat.api.wxbase.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

@ReqType("getUserInfo")
public class AuthUserInfoReq extends WeixinReqParam {
	private String openid;
	private String access_token;

	@Override
	public String toParams() {
		StringBuffer sb = new StringBuffer();
		sb.append("openid=").append(openid);
		sb.append("&access_token=").append(access_token);
		return sb.toString();
	}

	public AuthUserInfoReq() {

	}

	public AuthUserInfoReq(String openid, String access_token) {
		this.openid = openid;
		this.access_token = access_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

}
