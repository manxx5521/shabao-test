package com.xiaoshabao.wechat.api.wxuser.model;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 移动用户分组
 */
@ReqType("groupMoveUserById")
public class GroupMoveUserById extends WeixinReqParam {
	
	private String openid;
	
	private String to_groupid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTo_groupid() {
		return to_groupid;
	}

	public void setTo_groupid(String to_groupid) {
		this.to_groupid = to_groupid;
	}

	
}
