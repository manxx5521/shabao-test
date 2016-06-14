package com.xiaoshabao.wechat.api.wxuser.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;


/**
 * 获取用户列表
 */
@ReqType("getUserInfoList")
public class UserListGet extends WeixinReqParam {
	
	/**
	 * 第一个拉取的OPENID，不填默认从头开始拉取
	 */
	private String next_openid;

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
}
