package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.wechat.entity.BargainSuccess;
import com.xiaoshabao.wechat.entity.SubscriberEntity;


/**
 * 砍价信息返回
 */
public class BargainUser extends BargainSuccess {
	private SubscriberEntity user;

	public SubscriberEntity getUser() {
		return user;
	}

	public void setUser(SubscriberEntity user) {
		this.user = user;
	}
}
