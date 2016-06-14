package com.xiaoshabao.wechat.api.wxuser.model;

import java.util.List;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 
 * 批量移动用户
 */
@ReqType("groupMoveUser")
public class GroupMoveUser extends WeixinReqParam {
	
	private List<String> openid_list;
	
	private String to_groupid;


	public List<String> getOpenid_list() {
		return openid_list;
	}

	public void setOpenid_list(List<String> openid_list) {
		this.openid_list = openid_list;
	}

	public String getTo_groupid() {
		return to_groupid;
	}

	public void setTo_groupid(String to_groupid) {
		this.to_groupid = to_groupid;
	}

	
}
