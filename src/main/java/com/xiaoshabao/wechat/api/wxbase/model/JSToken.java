package com.xiaoshabao.wechat.api.wxbase.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;


@ReqType("jstoken")
public class JSToken extends WeixinReqParam{

	private String access_token;
	private String type="jsapi";
	
	@Override
	public String toParams() {
		StringBuffer sb=new StringBuffer();
		sb.append("access_token=");
		sb.append(access_token);
		sb.append("&type=");
		sb.append(type);
		return sb.toString();
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
