package com.xiaoshabao.wechat.api.wxuser.model;

import java.util.ArrayList;
import java.util.List;

import com.xiaoshabao.wechat.api.core.annotation.ReqType;
import com.xiaoshabao.wechat.api.core.req.WeixinReqParam;

/**
 * 获取用户基本信息
 */
@ReqType("getUserBaseInfoList")
public class UserBaseInfoGetList extends WeixinReqParam {
	/**
	 * 用户列表
	 */
	private List<UserInfoGetBean> user_list=new ArrayList<UserInfoGetBean>();

	public List<UserInfoGetBean> getUser_list() {
		return user_list;
	}

	public void setUser_list(List<UserInfoGetBean> user_list) {
		this.user_list = user_list;
	}

}
