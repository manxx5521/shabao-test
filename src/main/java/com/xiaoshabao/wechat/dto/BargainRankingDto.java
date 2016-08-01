package com.xiaoshabao.wechat.dto;

import com.xiaoshabao.wechat.entity.BargainJoinEntity;
import com.xiaoshabao.wechat.entity.SubscriberEntity;
/**
 * 排行榜信息
 */
public class BargainRankingDto extends BargainJoinEntity{
	private SubscriberEntity user;

	public SubscriberEntity getUser() {
		return user;
	}

	public void setUser(SubscriberEntity user) {
		this.user = user;
	}
}
